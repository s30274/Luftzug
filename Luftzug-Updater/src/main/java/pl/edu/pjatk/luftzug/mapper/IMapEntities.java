package pl.edu.pjatk.luftzug.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import pl.edu.pjatk.luftzug.client.contract.IHaveName;

public interface IMapEntities<TDto, TEntity> {
    TEntity map(TDto dto);
    TEntity map(TDto dto, TEntity entity);

    default String extractName(IHaveName dto){
        var name = dto.names().name();
        if(name!=null){
            return name.value();
        } else {
            return "";
        }
    }
}
