package net.avdw.eve.marketer;

import net.avdw.eve.tradeitem.TradeItem;
import net.avdw.eve.domain.TradeStatistic;
import net.avdw.eve.marketer.domain.Type;
import net.avdw.eve.marketer.domain.TypeStat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = IterableNonIterableUtil.class)
public interface MarketerMapper {
    MarketerMapper INSTANCE = Mappers.getMapper(MarketerMapper.class);

    List<TradeItem> toTradeItemList(Type[] types);

    @Mappings({
            @Mapping(target = "minPrice", source = "min"),
            @Mapping(target = "maxPrice", source = "max"),
            @Mapping(target = "fivePercentPrice", source = "fivePercent"),
            @Mapping(target = "average", source = "avg"),
            @Mapping(target = "weightedAverage", source = "wavg"),
            @Mapping(target = "standardDeviation", source = "stdDev")
    })
    TradeStatistic toTradeStatistic(TypeStat typeStat);

    @Mappings({
            @Mapping(target = "id", source = "type.buy.forQuery.types", qualifiedBy = FirstElement.class),
            @Mapping(target = "region.id", source = "type.buy.forQuery.regions", qualifiedBy = FirstElement.class),
            @Mapping(target = "solarSystem.id", source = "type.buy.forQuery.systems", qualifiedBy = FirstElement.class)
    })
    TradeItem toTradeItem(Type type);
}
