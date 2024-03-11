package com.dodo.bootpractice.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJpaMember is a Querydsl query type for JpaMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJpaMember extends EntityPathBase<JpaMember> {

    private static final long serialVersionUID = -1729382444L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJpaMember jpaMember = new QJpaMember("jpaMember");

    public final com.dodo.bootpractice.jpa.domain.embedded.QAddress address;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QJpaTeam team;

    public final StringPath username = createString("username");

    public final com.dodo.bootpractice.jpa.domain.embedded.QPeriod workPeriod;

    public QJpaMember(String variable) {
        this(JpaMember.class, forVariable(variable), INITS);
    }

    public QJpaMember(Path<? extends JpaMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJpaMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJpaMember(PathMetadata metadata, PathInits inits) {
        this(JpaMember.class, metadata, inits);
    }

    public QJpaMember(Class<? extends JpaMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.dodo.bootpractice.jpa.domain.embedded.QAddress(forProperty("address")) : null;
        this.team = inits.isInitialized("team") ? new QJpaTeam(forProperty("team")) : null;
        this.workPeriod = inits.isInitialized("workPeriod") ? new com.dodo.bootpractice.jpa.domain.embedded.QPeriod(forProperty("workPeriod")) : null;
    }

}

