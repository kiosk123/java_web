package entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1690149669L;

    public static final QMember member = new QMember("member1");

    public final StringPath auth = createString("auth");

    public final StringPath department = createString("department");

    public final StringPath id = createString("id");

    public final StringPath loginCnt = createString("loginCnt");

    public final StringPath phoneEx = createString("phoneEx");

    public final StringPath phoneIn = createString("phoneIn");

    public final StringPath pw = createString("pw");

    public final DatePath<java.sql.Date> pwUpdate = createDate("pwUpdate", java.sql.Date.class);

    public final DatePath<java.sql.Date> registDate = createDate("registDate", java.sql.Date.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

