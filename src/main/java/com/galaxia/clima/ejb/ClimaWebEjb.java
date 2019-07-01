package com.galaxia.clima.ejb;

import javax.ejb.Stateless;

import com.galaxia.clima.dao.ClimaDAO;

@Stateless
public class ClimaWebEjb {

	public String ws_get_tex() throws Exception {
		return ClimaDAO.ws_get_tex();
	}
}
