package spring.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.entity.PositionType;
import spring.service.PositionTypeService;

@Component
public class PosTypeIdToObjConverter implements Converter<Object, PositionType> {

    @Autowired
    PositionTypeService positionTypeService;

    public PositionType convert(Object element) {
        if (element.toString().isEmpty()) return null;
        Long id = Long.parseLong(element.toString());
        return positionTypeService.getByID(id);
    }
}
