package spring.dao.impl;

import spring.dao.PositionTypeDAO;
import spring.dao.common.GenericDAOImpl;
import spring.entity.PositionType;
import org.springframework.stereotype.Repository;

@Repository
public class PositionTypeDAOImpl extends GenericDAOImpl<PositionType, Long> implements PositionTypeDAO {}