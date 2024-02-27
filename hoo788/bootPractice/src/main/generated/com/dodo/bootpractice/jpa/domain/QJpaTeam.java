package com.dodo.bootpractice.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaTeam is a Querydsl query type for JpaTeam
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJpaTeam extends EntityPathBase<JpaTeam> {

    private static final long serialVersionUID = -966953449L;

    public static final QJpaTeam jpaTeam = new QJpaTeam("jpaTeam");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QJpaTeam(String variable) {
        super(JpaTeam.class, forVariable(variable));
    }

    public QJpaTeam(Path<? extends JpaTeam> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaTeam(PathMetadata metadata) {
        super(JpaTeam.class, metadata);
    }

}

