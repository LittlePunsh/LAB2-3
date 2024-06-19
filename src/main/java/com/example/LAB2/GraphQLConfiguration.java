package com.example.LAB2;

import com.example.LAB2.model.Guest;
import com.example.LAB2.repository.GuestRepository;
import graphql.schema.DataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Configuration
public class GraphQLConfiguration {

    private final GuestRepository guestRepository;
    private GraphQL graphQL;

    public GraphQLConfiguration(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        File schemaFile = new File("src/main/resources/graphql/schema.graphqls");
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        this.graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<Guest>> getAllGuestsFetcher = dataFetchingEnvironment -> guestRepository.findAll();
        DataFetcher<Guest> getGuestByIdFetcher = dataFetchingEnvironment -> {
            Long id = dataFetchingEnvironment.getArgument("id");
            return guestRepository.findById(id).orElse(null);
        };

        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("getAllGuests", getAllGuestsFetcher)
                        .dataFetcher("getGuestById", getGuestByIdFetcher))
                .build();
    }
}
