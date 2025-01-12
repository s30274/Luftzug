package pl.edu.pjatk.luftzug.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import pl.edu.pjatk.luftzug.client.contract.IHaveName;

public interface IMapEntities<TDto, TEntity> {
    TEntity map(TDto dto);
    TEntity map(TDto dto, TEntity entity);

    default String extractName(IHaveName dto){
        var names = dto.names().nameList();
        if(names!=null){
            return extractIfNameNotNull(names);
        } else {
            return "";
        }
    }

    default String extractIfNameNotNull(JsonNode names){
        if(names.size() == 2){
            return getNameIfNotNull(names);
        } else {
            return findEnglishName(names);
        }
    }

    default String getNameIfNotNull(JsonNode name){
        if(name.get("@LanguageCode")!=null){
            return name.get("$").asText();
        } else {
            return "";
        }
    }

    default String findEnglishName(JsonNode names){
        for(var name : names){
            if(name.get("@LanguageCode")!=null) {
                return getNameIfLanguageCodeIsCorrect(name);
            }
        }
        return "";
    }

    default String getNameIfLanguageCodeIsCorrect(JsonNode name){
        if (name.get("@LanguageCode").asText().equals("EN")) {
            return getNameIfNotNull(name);
        } else {
            return "";
        }
    }
}
