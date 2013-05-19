
package db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.dto.Project;


public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
    private Class<T> persistentClass;
    private Session session;
    public GenericDAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
        
     }

    public GenericDAOImpl(Class<T> type)  {
        persistentClass=type;
    }

    public void setSession(Session s) {
        this.session = s;
    }

    protected Session getSession() {
        if (session == null || !session.isOpen())
            session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(ID id) {
        System.out.println("getById");
        T entity;
        entity = (T) getSession().load(getPersistentClass(), id);
        getSession().evict(entity);
        getSession().close();
        clear();
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
         System.out.println("findAll");
    	Criteria crit = getSession().createCriteria(getPersistentClass());
        clear();
        List<T> projectList = crit.list();
        getSession().close();
    	return projectList;
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public T create(T entity) {
    	Transaction tx =null;
    	try{
    		          
            tx = getSession().beginTransaction();
            getSession().save(entity);
            tx.commit();
            getSession().refresh(entity);
            getSession().evict(entity);
            clear();
    	}catch (HibernateException e) {
	        System.out.println(e);		
            if(tx != null) tx.rollback();
            getSession().close();
			entity=null;
			session=null;
            e.printStackTrace();
		}catch(Exception ex)
		{	ex.printStackTrace();
			System.out.println("After Exception..bad");
			 if(tx!=null) tx.rollback();
				entity=null;
		}
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean update(T entity) {
         System.out.println("update");
    	Transaction tx =null;
    	boolean status = false;
    	try{
            tx = getSession().beginTransaction();
            
            getSession().update(entity);
            tx.commit();
            getSession().evict(entity);
            clear();
            status=true;
    	}catch (HibernateException e) {
			if(tx!=null) tx.rollback();
			 getSession().close();
				
				session=null;
		   e.printStackTrace();	
		}catch(Exception ex)
		{	ex.printStackTrace();
			System.out.println("After Exception..bad");
			 if(tx!=null) tx.rollback();
			 getSession().close();
			 
		}
        return status;
    }
    
    @Override
    public boolean delete(T entity) {
         System.out.println("delete");
        Transaction tx =null;
    	boolean status = false;
    	try{
            tx = getSession().beginTransaction();
            getSession().delete(entity);
            tx.commit();
            getSession().evict(entity);
            status=true;
            
    	}catch (HibernateException e) {
			if(tx!=null) tx.rollback();
                        e.printStackTrace();
		}
        return status;
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

}
