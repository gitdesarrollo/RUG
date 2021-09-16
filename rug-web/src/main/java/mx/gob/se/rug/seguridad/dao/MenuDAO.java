package mx.gob.se.rug.seguridad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import mx.gob.se.rug.dao.ConexionBD;
import mx.gob.se.rug.garantia.dao.GarantiasDAO;
import mx.gob.se.rug.inscripcion.to.VigenciaTO;
import mx.gob.se.rug.masiva.to.string.Vigencia;
import mx.gob.se.rug.seguridad.to.MenuTO;
import mx.gob.se.rug.to.UsuarioTO;
import mx.gob.se.rug.util.MyLogger;

public class MenuDAO {

	public UsuarioTO getPerfil(UsuarioTO usuarioTO) {
		if(usuarioTO.getPersona()!=null){
		ConexionBD bd = new ConexionBD();
		Connection connection = null;
		String sql = "select CVE_PERFIL from V_USUARIO_SESION_RUG where ID_PERSONA=?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			connection = bd.getConnection();
			ps = connection.prepareStatement(sql);
                       
			MyLogger.Logger.log(Level.INFO, "idpersona 3"		+ usuarioTO.getPersona().getIdPersona());
			ps.setInt(1, usuarioTO.getPersona().getIdPersona());
			rs = ps.executeQuery();
			if (rs.next()) {
				usuarioTO.getPersona().setPerfil(rs.getString("CVE_PERFIL"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			bd.close(connection, rs, ps);
		}
		}else{
			MyLogger.Logger.log(Level.WARNING, "MENU DAO ID PERSONA NULL ------ -- ----- -- -----");
		}
		return usuarioTO;
	}

	public MenuTO getMenuPrincipal(MenuTO menuTO, UsuarioTO usuarioTO) {
		usuarioTO=	 getPerfil( usuarioTO);
		if (!usuarioTO.getPersona().getPerfil().equalsIgnoreCase("AUTORIDAD")
				&& !usuarioTO.getPersona().getPerfil().equalsIgnoreCase("ROOT")) {
			if (getAcreedoresByIdUser(usuarioTO.getPersona().getIdPersona())
					.booleanValue()) {
				MyLogger.Logger.log(Level.INFO, "getMenuPrincipal --INICIA acreedor___");
				ConexionBD bd = new ConexionBD();
				Connection connection = null;
				List<String> html = new ArrayList<String>();
				String sql = "Select DISTINCT HTML ,ORDEN from V_USUARIO_ACREEDOR_GRUPOS Where ID_SUB_USUARIO = ? AND ID_RECURSO = 1 ORDER BY ORDEN";
				ResultSet rs = null;
				PreparedStatement ps = null;
				try {
					connection = bd.getConnection();
					ps = connection.prepareStatement(sql);
					MyLogger.Logger.log(Level.INFO, "idpersona 1"
							+ usuarioTO.getPersona().getIdPersona());

					ps.setInt(1, usuarioTO.getPersona().getIdPersona());

					rs = ps.executeQuery();
					while (rs.next()) {
						html.add(rs.getString("HTML").replace("@contextPath",
								menuTO.getContextPath()));
					}
					menuTO.setHtml(html);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					bd.close(connection, rs, ps);
				}
			} else {
				MyLogger.Logger.log(Level.INFO, "getMenuPrincipal --INICIA NORMAL___");
				menuTO = getMenu(menuTO, usuarioTO);
			}
		} else {
			MyLogger.Logger.log(Level.INFO, "getMenuPrincipal --INICIA NORMAL___");
			menuTO = getMenu(menuTO, usuarioTO);
		}
		return menuTO;
	}

	public MenuTO getMenuSub(Integer idAcreedor, UsuarioTO usuarioTO,
			MenuTO menuTO, Boolean vigencia) {
		MyLogger.Logger.log(Level.INFO, "-------------------------------------------------------------subMenu-----------------");
		usuarioTO=	 getPerfil( usuarioTO);
			if (!usuarioTO.getPersona().getPerfil().equalsIgnoreCase("AUTORIDAD")
				&& !usuarioTO.getPersona().getPerfil().equalsIgnoreCase("ROOT")) {
			MyLogger.Logger.log(Level.INFO, "submenu-----Inicia subAcreedor");
			// if(getAcreedoresByIdUser(usuarioTO.getPersona().getIdPersona()).booleanValue()){
			MyLogger.Logger.log(Level.INFO, "submenu de  acreedor test" + idAcreedor);
			GarantiasDAO gdao = new GarantiasDAO();
			idAcreedor = gdao.getCuentaMaestra(idAcreedor.longValue()).intValue();
			ConexionBD bd = new ConexionBD();
			Connection connection = null;
			List<String> html = new ArrayList<String>();
			String sql = "Select DISTINCT HTML ,ORDEN  from V_USUARIO_ACREEDOR_GRUPOS Where ID_ACREEDOR = ? AND ID_SUB_USUARIO = ? AND ID_RECURSO = 2 ";
			if(vigencia!=null && !vigencia) {
				sql	+= " AND ID_PRIVILEGIO = 9";
			}
			sql	+= " ORDER BY ORDEN";
			ResultSet rs = null;
			PreparedStatement ps = null;
			try {
				connection = bd.getConnection();
				ps = connection.prepareStatement(sql);
				MyLogger.Logger.log(Level.INFO, "idpersona otro"
						+ usuarioTO.getPersona().getIdPersona());

				ps.setInt(1, idAcreedor);
				ps.setInt(2, usuarioTO.getPersona().getIdPersona());

				rs = ps.executeQuery();
				while (rs.next()) {
					html.add(rs.getString("HTML").replace("@contextPath",
							menuTO.getContextPath()));
				}
				menuTO.setHtml(html);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				bd.close(connection, rs, ps);
			}
		} else {
			MyLogger.Logger.log(Level.INFO, "submenu de  grupo definido------------------------------------");
			menuTO = getMenu(menuTO, usuarioTO);
		}
		return menuTO;
	}
	
	
	public MenuTO getMenuAnotacionSinGarantia(UsuarioTO usuarioTO,
			MenuTO menuTO) {
		MyLogger.Logger.log(Level.INFO, "-------------------------------------------------------------subMenuAnotacionSinGarantia-----------------");
		usuarioTO=	 getPerfil( usuarioTO);
			if (!usuarioTO.getPersona().getPerfil().equalsIgnoreCase("AUTORIDAD")
				&& !usuarioTO.getPersona().getPerfil().equalsIgnoreCase("ROOT")) {
			MyLogger.Logger.log(Level.INFO, "submenuAnotacionSinGarantia-----Inicia subAcreedor");
			// if(getAcreedoresByIdUser(usuarioTO.getPersona().getIdPersona()).booleanValue()){
			MyLogger.Logger.log(Level.INFO, "submenu de  acreedor test 2"+ usuarioTO.getPersona().getIdPersona());
			ConexionBD bd = new ConexionBD();
			Connection connection = null;
			List<String> html = new ArrayList<String>();
			String sql = "Select DISTINCT HTML ,ORDEN  from V_USUARIO_ACREEDOR_GRUPOS Where ID_SUB_USUARIO = ? AND ID_RECURSO = 11 ORDER BY ORDEN";
			ResultSet rs = null;
			PreparedStatement ps = null;
			try {
				connection = bd.getConnection();
				ps = connection.prepareStatement(sql);
				MyLogger.Logger.log(Level.INFO, "idpersona 2"
						+ usuarioTO.getPersona().getIdPersona());

				ps.setInt(1, usuarioTO.getPersona().getIdPersona());

				rs = ps.executeQuery();
				while (rs.next()) {
					html.add(rs.getString("HTML").replace("@contextPath",
							menuTO.getContextPath()));
				}
				menuTO.setHtml(html);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				bd.close(connection, rs, ps);
			}
		} else {
			MyLogger.Logger.log(Level.INFO, "submenuAnotacionSinGarantia de  grupo definido------------------------------------");
			menuTO = getMenu(menuTO, usuarioTO);
		}
		return menuTO;
	}

	public Boolean getJudicialMenu(Integer idUser) {
		ConexionBD bd = new ConexionBD();
		Connection connection = null;
		String sql = "select is_judicial from rug_secu_usuarios where id_persona = ? and is_judicial is not null";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Boolean flag = new Boolean(false);
		try {
			connection = bd.getConnection();
			ps=connection.prepareStatement(sql);
			MyLogger.Logger.log(Level.INFO, "idpersona 3" + idUser);

			ps.setInt(1, idUser);

			rs = ps.executeQuery();
			if (rs.next()) {
				MyLogger.Logger.log(Level.INFO, "is_judicial:::::"
						+ rs.getString("is_judicial"));
				flag = new Boolean(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			bd.close(connection, rs, ps);
		}
		return flag;
	}

	public Boolean getAcreedoresByIdUser(Integer idUser) {

		ConexionBD bd = new ConexionBD();
		Connection connection = null;
		String sql = "SELECT DISTINCT ID_ACREEDOR FROM V_USUARIO_ACREEDOR  WHERE ID_USUARIO =?  AND B_FIRMADO = 'Y'";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Boolean flag = new Boolean(false);
		try {
			connection = bd.getConnection();
			ps=connection.prepareStatement(sql);
			MyLogger.Logger.log(Level.INFO, "idpersona 4" + idUser);

			ps.setInt(1, idUser);

			rs = ps.executeQuery();
			if (rs.next()) {
				MyLogger.Logger.log(Level.INFO, "id_acreedor::::: Certificaciones "
						+ rs.getString("id_acreedor"));
				flag = new Boolean(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			bd.close(connection, rs, ps);
		}
		return flag;

	}

	public MenuTO getMenu(MenuTO menuTO, UsuarioTO usuarioTO) {
		ConexionBD bd = new ConexionBD();
		Connection connection = null;
		List<String> html = new ArrayList<String>();
		String sql = "SELECT  RP.HTML" + " FROM RUG_SECU_USUARIOS RSU,"
				+ " RUG_GRUPOS RG," + " RUG_REL_GRUPO_PRIVILEGIO RRGP,"
				+ " RUG_PRIVILEGIOS RP," + " RUG_SECU_PERFILES_USUARIO RSPU,"
				+ " RUG_RECURSOS RR" + " WHERE RSU.ID_GRUPO = RG.ID_GRUPO"
				+ " AND RR.ID_RECURSO = RP.ID_RECURSO"
				+ " AND RSU.ID_GRUPO = RRGP.ID_GRUPO"
				+ " AND RRGP.ID_PRIVILEGIO = RP.ID_PRIVILEGIO"
				+ " AND RSU.CVE_USUARIO = RSPU.CVE_USUARIO"
				+ " AND RSU.ID_PERSONA=?" + " AND RP.ID_RECURSO=?"
				+ " ORDER BY RP.ORDEN";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			connection = bd.getConnection();
			ps = connection.prepareStatement(sql);
			MyLogger.Logger.log(Level.INFO, "idRecurso" + menuTO.getIdMenu());
			MyLogger.Logger.log(Level.INFO, "idpersona 5"
					+ usuarioTO.getPersona().getIdPersona());

			ps.setInt(1, usuarioTO.getPersona().getIdPersona());
			ps.setInt(2, menuTO.getIdMenu());

			rs = ps.executeQuery();
			while (rs.next()) {
				html.add(rs.getString("HTML").replace("@contextPath",
						menuTO.getContextPath()));
			}
			menuTO.setHtml(html);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			bd.close(connection, rs, ps);
		}
		return menuTO;
	}

}
