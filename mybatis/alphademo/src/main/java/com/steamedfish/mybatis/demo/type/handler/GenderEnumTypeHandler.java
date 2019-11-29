package com.steamedfish.mybatis.demo.type.handler;

import com.steamedfish.mybatis.demo.enums.GenderEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈GenderEnum 转换器〉
 *
 * @author steamedfish
 * @create 2019/11/29
 * @since 1.0.0
 */
@MappedJdbcTypes({JdbcType.TINYINT})
@MappedTypes({GenderEnum.class})
public class GenderEnumTypeHandler extends BaseTypeHandler<GenderEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, GenderEnum parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setInt(i, parameter.getCode());
        } else {
            ps.setObject(i, parameter.getCode(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public GenderEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int rsInt = rs.getInt(columnName);
        return GenderEnum.forCode(rsInt);
    }

    @Override
    public GenderEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int rsInt = rs.getInt(columnIndex);
        return GenderEnum.forCode(rsInt);
    }

    @Override
    public GenderEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int csInt = cs.getInt(columnIndex);
        return GenderEnum.forCode(csInt);
    }
}