package org.doit.multiplication;

import org.doit.multiplication.domain.models.ChallengeAttempt;
import org.doit.multiplication.domain.models.User;
import org.doit.multiplication.infrastructure.in.rest.dto.ChallengeAttemptDTO;
import org.doit.multiplication.infrastructure.in.rest.dto.VerifyChallengeAttemptRequest;
import org.doit.multiplication.infrastructure.out.amqp.dto.ChallengeSolvedEventDTO;
import org.doit.multiplication.infrastructure.out.rest.dto.ChallengeSolvedDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.DestinationSetter;
import org.modelmapper.spi.SourceGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    public static <S, D> TypeMap<S, D> createTypeMap(ModelMapper modelMapper, Class<S> sourceClass, Class<D> destinationClass) {
        return modelMapper.createTypeMap(sourceClass, destinationClass);
    }

    public static <S, D, V> void addMapping(TypeMap<S, D> typeMap, SourceGetter<S> source,  DestinationSetter<D, V> target) {
        typeMap.addMappings(mapper -> {
            mapper.map(source, target);
        });
    }

    @Bean
    public ModelMapper modelMapper() {
        var mapperInstance =  new ModelMapper();

        var typeMapAttempt = createTypeMap(mapperInstance, VerifyChallengeAttemptRequest.class, ChallengeAttempt.class);
        addMapping(typeMapAttempt, src -> new User(null, src.getUserAlias()), ChallengeAttempt::setUser);

        var typeMapVerify = createTypeMap(mapperInstance, ChallengeAttempt.class, ChallengeAttemptDTO.class);
        addMapping(typeMapVerify, src -> src.getUser().getAlias(), ChallengeAttemptDTO::setUserAlias);

        var typeMapSolved = createTypeMap(mapperInstance, ChallengeAttempt.class, ChallengeSolvedDTO.class);
        addMapping(typeMapSolved, src -> src.getUser().getAlias(), ChallengeSolvedDTO::setUserAlias);
        addMapping(typeMapSolved, src -> src.getUser().getId(), ChallengeSolvedDTO::setUserId);

        var typeMapEventSolved = createTypeMap(mapperInstance, ChallengeAttempt.class, ChallengeSolvedEventDTO.class);
        addMapping(typeMapEventSolved, src -> src.getUser().getAlias(), ChallengeSolvedEventDTO::setUserAlias);
        addMapping(typeMapEventSolved, src -> src.getUser().getId(), ChallengeSolvedEventDTO::setUserId);

        return mapperInstance;
    }
}
