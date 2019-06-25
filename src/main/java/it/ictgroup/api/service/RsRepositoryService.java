package it.ictgroup.api.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;
import org.jboss.logging.Logger;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static io.quarkus.panache.common.Page.of;

public abstract class RsRepositoryService<T> extends RsResponseService implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Logger logger = Logger.getLogger(getClass());

    public RsRepositoryService() {

    }


    protected void prePersist(T object) throws Exception {
    }

    @POST
    @Transactional
    public Response persist(T object) throws Exception {
        try {
            prePersist(object);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return jsonMessageResponse(Status.BAD_REQUEST, e);
        }
        try {
            JpaOperations.persist(object);
            if (object == null || getId(object) == null) {
                logger.error("Failed to create resource: " + object);
                return jsonErrorMessageResponse(object);
            } else {
                return Response.status(Status.OK).entity(object).build();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return jsonErrorMessageResponse(object);
        } finally {
            try {
                postPersist(object);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    protected void postPersist(T object) throws Exception {
    }

    protected void postFetch(T object) throws Exception {
    }

    /*
     * R
     */
    @GET
    @Path("/{id}")
    @Transactional
    public Response fetch(@PathParam("id") String id) {
        try {
            T t = (T) JpaOperations.findById(getEntityType(), id);
            if (t == null) {
                return jsonMessageResponse(Status.NOT_FOUND, "object not found using: " + id);
            } else {
                try {
                    postFetch(t);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                return Response.status(Status.OK).entity(t).build();
            }
        } catch (NoResultException e) {
            logger.error(e.getMessage());
            return jsonMessageResponse(Status.NOT_FOUND, id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return jsonErrorMessageResponse(e);
        }
    }

    /*
     * U
     */
    protected T preUpdate(T object) throws Exception {
        return object;
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") String id, T object) throws Exception {
        logger.info("@PUT update:" + object.toString());
        try {
            object = preUpdate(object);
        } catch (Exception e) {
            return jsonMessageResponse(Status.BAD_REQUEST, e);
        }
        try {
            JpaOperations.getEntityManager().merge(object);
            return Response.status(Status.OK).entity(object).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return jsonErrorMessageResponse(object);
        } finally {
            try {
                postUpdate(object);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * concepita per chiamare robe async dopo l'update (o cmq robe fuori dalla tx principale che non rollbacka se erorri qui)
     *
     * @param object
     * @throws Exception
     */
    protected void postUpdate(T object) throws Exception {
    }

    /*
     * D
     */

    protected void preDelete(String id) throws Exception {
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") String id) throws Exception {
        logger.info("@DELETE:" + id);
        try {
            preDelete(id);
        } catch (Exception e) {
            return jsonMessageResponse(Status.BAD_REQUEST, e);
        }
        T t;
        try {
            t = (T) JpaOperations.find(getEntityType(), id);
            if (t == null) {
                return jsonMessageResponse(Status.NOT_FOUND, id);
            }
        } catch (Exception e) {
            return jsonMessageResponse(Status.BAD_REQUEST, e);
        }
        try {
            JpaOperations.delete(t);
            postDelete(id);
            return jsonMessageResponse(Status.NO_CONTENT, id);
        } catch (NoResultException e) {
            logger.error(e.getMessage());
            return jsonMessageResponse(Status.NOT_FOUND, id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return jsonErrorMessageResponse(e);
        }
    }


    protected void postDelete(String id) throws Exception {
    }

    /*
     * E
     */
    @GET
    @Path("/{id}/exist")
    public Response exist(@PathParam("id") String id) {
        logger.info("@GET exist:" + id);
        try {
            boolean exist = JpaOperations.find(getEntityType(), id) != null;
            if (!exist) {
                return jsonMessageResponse(Status.NOT_FOUND, id);
            } else {
                return jsonMessageResponse(Status.OK, id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return jsonErrorMessageResponse(e);
        }
    }


    @GET
    @Path("/listSize")
    @Transactional
    public Response getListSize(@Context UriInfo ui) {
        try {
            PanacheQuery<T> search = getSearch(ui, null);
            long listSize = search.count();
            return Response.status(Status.OK).entity(listSize)
                    .header("Access-Control-Expose-Headers", "listSize")
                    .header("listSize", listSize).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return jsonErrorMessageResponse(e);
        }
    }

    /*
     * Q
     */

    @GET
    @Transactional
    public Response getList(
            @DefaultValue("0") @QueryParam("startRow") Integer startRow,
            @DefaultValue("10") @QueryParam("pageSize") Integer pageSize,
            @QueryParam("orderBy") String orderBy, @Context UriInfo ui) {
        try {
            PanacheQuery<T> search = getSearch(ui, orderBy);
            long listSize = search.count();
            List<T> list = search.page(of(startRow, pageSize)).list();

            postList(list);
            return Response
                    .status(Status.OK)
                    .entity(list)
                    .header("Access-Control-Expose-Headers",
                            "startRow, pageSize, listSize").header("startRow", startRow)
                    .header("pageSize", pageSize).header("listSize", listSize).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return jsonErrorMessageResponse(e);
        }
    }

    protected void postList(List<T> list) throws Exception {
    }


    public abstract Object getId(T t);


    public abstract PanacheQuery<T> getSearch(UriInfo ui, String orderBy);

    // protected abstract Class<T> getEntityType();
    @SuppressWarnings("unchecked")
    protected Class<T> getEntityType() throws Exception {
//        ParameterizedType parameterizedType = (ParameterizedType) getClass()
//                .getGenericSuperclass();
//        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Type genericSuperClass = getClass().getGenericSuperclass();

        ParameterizedType parametrizedType = null;
        while (parametrizedType == null) {
            if ((genericSuperClass instanceof ParameterizedType)) {
                parametrizedType = (ParameterizedType) genericSuperClass;
            } else {
                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
            }
        }

        return (Class<T>) parametrizedType.getActualTypeArguments()[0];
    }


}
