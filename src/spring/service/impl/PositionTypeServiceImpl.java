package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.PositionTypeDAO;
import spring.dao.common.GenericDAO;
import spring.entity.PositionType;
import spring.service.PositionTypeService;
import spring.service.common.GenericServiceImpl;

@Service
@Transactional
public class PositionTypeServiceImpl extends GenericServiceImpl<PositionType, Long> implements PositionTypeService {

    private PositionTypeDAO positionTypeDAO;

    @Autowired
    public void setPositionTypeDAO(PositionTypeDAO positionTypeDAO) {
        this.positionTypeDAO = positionTypeDAO;
    }

    @Override
    protected GenericDAO<PositionType, Long> getDAO() {
        return positionTypeDAO;
    }
}
