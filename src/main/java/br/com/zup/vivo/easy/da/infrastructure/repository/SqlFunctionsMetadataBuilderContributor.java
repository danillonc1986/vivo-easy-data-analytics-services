package br.com.zup.vivo.easy.da.infrastructure.repository;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {

    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("compare_tsvector_plainto_tsquery",
                new SQLFunctionTemplate(BooleanType.INSTANCE, "document @@ plainto_tsquery('pg_catalog.portuguese',?1::text)"));
    }
}
