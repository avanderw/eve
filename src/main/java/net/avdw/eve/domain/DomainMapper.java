package net.avdw.eve.domain;

import net.avdw.eve.marketer.MarketerRequest;
import net.avdw.eve.domain.tradeitem.TradeItem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DomainMapper {
    DomainMapper INSTANCE = Mappers.getMapper(DomainMapper.class);

    @Mappings({
            @Mapping(target = "solarSystem", source = "marketerRequest.solarSystem"),
            @Mapping(target = "region", source = "marketerRequest.region")
    })
    TradeItem toTradeItem(TradeItem from, @MappingTarget TradeItem to, MarketerRequest marketerRequest);
}
