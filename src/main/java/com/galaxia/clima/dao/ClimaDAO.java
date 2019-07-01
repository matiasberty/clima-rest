package com.galaxia.clima.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxia.clima.util.InterfaceDb;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ClimaDAO {
	private static final Logger LOG = LoggerFactory.getLogger(ClimaDAO.class);

	public static String ws_get_tex() throws Exception {
		Connection con = null;
		JsonArray jarr = new JsonArray();
		JsonObject jo = new JsonObject();
		JsonObject jores = new JsonObject();
		Statement stmt = null;
		List<String> res = new ArrayList<>();
		try {
			con = InterfaceDb.getConnection();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT tex_cod FROM tex WHERE tex_envio_desde_portal = 1");
			boolean more = rs.next();
			while (more) {
				jo = new JsonObject();
				jo.addProperty("tex_cod", rs.getString("tex_cod"));
				jarr.add(jo);
				more = rs.next();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			if (con != null) {
				con.rollback();
			}
			LOG.error(e.getMessage(), e);
			jores.addProperty("success", "false");
			jores.addProperty("error", "Error al realizar la búsqueda:" + e.getMessage());
			jores.addProperty("data", "");
			return jores.toString();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		jores.addProperty("success", "true");
		jores.addProperty("error", "");
		jores.add("data", jarr);

		return jores.toString();
	}
}
