package org.lopatka.idonc.dao;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.lopatka.idonc.model.data.IdoncPart;
import org.lopatka.idonc.model.data.IdoncProject;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {

	public int count() {
		Criteria crit = getSession().createCriteria(IdoncProject.class);
		crit.setProjection(Projections.rowCount());
		Integer result = (Integer) crit.uniqueResult();
		return result;
	}

	//@SuppressWarnings("unchecked")
	public List<IdoncProject> get(int first, int count) {
		Criteria  crit = getSession().createCriteria(IdoncProject.class);
		crit.setMaxResults(count);
		crit.setFirstResult(first);
       /* List<IdoncProject> l = crit.list();
        List<IdoncPart> tL = new ArrayList<IdoncPart>();
        for (IdoncProject iP : l) {
            iP.setPartsToProcess(tL);
            iP.setProcessedParts(tL);
        }
		return l;
        */
        return crit.list();
	}

	public IdoncProject load(long id) {
		return (IdoncProject) getSession().get(IdoncProject.class, new Long(id));
	}

	public void add(IdoncProject project) {
		getSession().save(project);
	}

}
