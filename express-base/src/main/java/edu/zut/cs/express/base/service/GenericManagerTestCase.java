package edu.zut.cs.express.base.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.zut.cs.express.base.BaseAbstractTestCase;
import edu.zut.cs.express.base.domain.BaseEntity;

@ContextConfiguration(locations = { "classpath:/applicationContextTest-resources.xml",
		"classpath:/applicationContext-dao.xml", "classpath:/applicationContext-service.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@Transactional
public abstract class GenericManagerTestCase<PK extends Serializable, T extends BaseEntity, M extends GenericManager<T, PK>>
		extends BaseAbstractTestCase {

	protected T entity;
	
	protected List<T> list;

	/**
	 * Logger for this class
	 */
	final Logger logger = LogManager.getLogger(this.getClass().getName());

	protected M manager;

	private Class<T> persistentClass;

	public GenericManagerTestCase(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Before
	public void setUp() throws Exception {
		this.entity = this.persistentClass.getDeclaredConstructor().newInstance();

	}

	@Test
	public void testCRUD() {
		if (this.entity == null) {
			try {
				this.entity = this.persistentClass.getDeclaredConstructor().newInstance();
				T newEntity = this.manager.save(this.entity);
				@SuppressWarnings("unchecked")
				PK entity_id = (PK) newEntity.getId();
				T foundEntity = this.manager.findById(entity_id);
				assertEquals(newEntity, foundEntity);

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void assertEquals(T newEntity, T foundEntity) {
		// TODO Auto-generated method stub
		
	}

}
