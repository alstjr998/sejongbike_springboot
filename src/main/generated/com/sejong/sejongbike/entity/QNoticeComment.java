package com.sejong.sejongbike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeComment is a Querydsl query type for NoticeComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeComment extends EntityPathBase<NoticeComment> {

    private static final long serialVersionUID = 1128409736L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeComment noticeComment = new QNoticeComment("noticeComment");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final QNotice notice;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> subTime = _super.subTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QNoticeComment(String variable) {
        this(NoticeComment.class, forVariable(variable), INITS);
    }

    public QNoticeComment(Path<? extends NoticeComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeComment(PathMetadata metadata, PathInits inits) {
        this(NoticeComment.class, metadata, inits);
    }

    public QNoticeComment(Class<? extends NoticeComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.notice = inits.isInitialized("notice") ? new QNotice(forProperty("notice")) : null;
    }

}

