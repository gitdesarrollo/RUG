<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Busqueda por CURP" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="Ingrese la Clave �nica de Registro de Poblaci�n (CURP) del Otorgante de la Garant�a Mobiliaria. El sistema buscar� con base en la CURP del otorgante con la cual se haya matriculado a �ste al momento de realizar la inscripci�n de la garant�a mobiliaria." /> 
			<br>
		</td>
	</tr>
	
	<tr>
		<td class="tituloInteriorRojo">	
			<br class="tituloInteriorRojo"><s:text name="Fundamento Legal" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"	width="500" height="3" />
			<br>
		</td>	
	</tr>	
	<tr>
		<td  class="texto_general">			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/Decreto de Reforma del Reglamento del Registro Publico de Comercio.pdf"
				class="ver_todas3" target="_blank"> <b><!--s:text name="Reglamento del Registro Publico de Comercio" /--></b>
				</a>
			<br>
		</td>	
	</tr>		
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="<b>Art�culo 30 Bis 1</b>
Los Asientos se llevar�n a cabo en el folio electr�nico del Otorgante.
Para efectos del art�culo 21, fracci�n XX del C�digo de Comercio, se entiende que el folio electr�nico antes referido es el que reside en la base de datos nacional del RUG prevista en el art�culo 32 Bis 3 del C�digo de Comercio. En caso de que el Otorgante sea una persona f�sica que no se encuentre matriculada en el Registro, el Sistema la matricular� de oficio para efectos del RUG en la base de datos nacional del RUG.<br/>
Los Asientos quedar�n realizados en el momento en el que sean firmados electr�nicamente por quien los realiza, cuya fecha y hora quedar� establecida en el sello digital de tiempo contenido en la boleta que emita el Sistema.<br/>
Toda consulta y solicitud de certificaci�n de una Garant�a Mobiliaria inscrita en el RUG deber� llevarse a cabo exclusivamente conforme al art�culo 34 de este Reglamento.<br/>" />
			 <br>
		</td>
	</tr>	

	<tr>
		<td>	
			<br class="texto_general"> 	
				<s:text name="Ejemplo CSCK800807DDFSRC93" />
			<br>
		</td>	
	</tr>	
</table>
