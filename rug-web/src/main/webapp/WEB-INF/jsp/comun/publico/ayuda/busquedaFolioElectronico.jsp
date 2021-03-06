<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Folio Electr?nico del Otorgante" />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name="El Folio Electr?nico es el expediente electr?nico de un Otorgante en el que se contienen los asientos relativos a Garant?as Mobiliarias y las anotaciones. Dicho folio electr?nico reside en la base de datos nacional del RUG prevista expresamente en el C?digo de Comercio." /> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">
			<br>
				<s:text name="Si el Otorgante es una Persona Moral, ingrese el n?mero de Folio que fue asignado al Otorgante por el Registro P?blico de Comercio (aplica tambi?n para extranjeros)." />
			<br>
		</td>
	</tr>		
	<tr>
		<td align="justify">	
			<br>
				<s:text name="Si el Otorgante es Persona F?sica, ingrese el n?mero de Folio que fue asignado al Otorgante por el RUG (aplica tambi?n para extranjeros)." />
			<br>	
		</td>
	</tr>	
	
	
	<tr>
		<td class="tituloInteriorRojo">	
			<br class="tituloInteriorRojo"><s:text name="Fundamento Legal" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"	width="500" height="3" />
			<br>
		</td>	
	</tr>	
	<tr>
		<td  class="texto_general">			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/legislacion/CODIGO DE COMERCIO.pdf"
				class="ver_todas3" target="_blank"> <b><s:text name="C?digo de Comercio" /></b>
				</a>
			<br>
		</td>	
	</tr>	
	
	<tr>
		<td align="justify">	
			<br>
				<s:text name="<b>Art?culo 21.-</b> Existir? un folio electr?nico por cada comerciante o sociedad, en el que se anotar?n:" />
			<br>	
		</td>
	</tr>
	
	<tr>
		<td>	
			<br>
				<s:text name="..." />
			<br>	
		</td>
	</tr>
	
	<tr>
		<td align="justify">	
			<br>
				<s:text name="<b>XX.-</b> Las garant?as mobiliarias que hubiere otorgado, as? como los actos jur?dicos por los que constituya un privilegio especial o derecho de retenci?n sobre bienes muebles a favor de terceros, en los t?rminos de lo dispuesto por los art?culos 32 bis 1 a 32 bis 9 del presente Cap?tulo. " />
			<br>	
		</td>
	</tr>
	
	
	<tr>
		<td  class="texto_general">			
			<br><a
				href="<%= request.getContextPath() %>/resources/pdf/Decreto de Reforma del Reglamento del Registro Publico de Comercio.pdf"
				class="ver_todas3" target="_blank"> <b><s:text name="Reglamento del Registro Publico de Comercio" /></b>
				</a>
			<br>
		</td>	
	</tr>		
	<tr>
		<td align="justify">			
			<br class="texto_general">
				<s:text	name="<b>Art?culo 30 bis 1 .-</b> Los Asientos se llevar?n a cabo en el folio electr?nico del Otorgante. Para efectos del art?culo 21, fracci?n XX del C?digo de Comercio, se entiende que el folio electr?nico antes referido es el que reside en la base de datos nacional del RUG prevista en el art?culo 32 Bis 3 del C?digo de Comercio. En caso de que el Otorgante sea una persona f?sica que no se encuentre matriculada en el Registro, el Sistema la matricular? de oficio para efectos del RUG en la base de datos nacional del RUG." />
			 <br>
		</td>
		
	</tr>	
	<tr>
		<td align="justify">			 
			<br class="texto_general"> 
				<s:text name="Toda consulta y solicitud de certificaci?n de una Garant?a Mobiliaria inscrita en el RUG deber? llevarse a cabo exclusivamente conforme al art?culo 34 de este Reglamento." />
			<br>
		</td>	
	</tr>		
		

</table>
