<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" width="90%" height="90%"
	border="0" valign="top">
	<tr>
		<td class="tituloInteriorRojo">
			<br class="tituloInteriorRojo"><s:text name="Denominaci�n o Raz�n Social" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"
				width="500" height="3" /><br>
		</td>
	</tr>
	<tr>
		<td align="justify">		
			<br class="texto_general">
				<s:text	name=" <b>Denominaci�n</b> es el "/> "nombre" 
				<s:text name=" de una persona moral que la identifica para todos los efectos legales. Se integra por una o varias palabras, normalmente vinculadas a la actividad desarrollada, y no lleva el nombre de ning�n socio"/> 
			<br>
		</td>
	</tr>
	<tr>
		<td align="justify">
			<br class="texto_general">
				<s:text name="<b>Raz�n social</b> es el "/> "nombre" 
				<s:text name=" que se le da a una persona moral integrado por el nombre de uno o m�s de sus socios." />
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
				href="<%= request.getContextPath() %>/resources/pdf/legislacion/LEY GENERAL DE SOCIEDADES MERCANTILES.pdf"
				class="ver_todas3" target="_blank"> <b><s:text name="Ley General de Sociedades Mercantiles" /></b>
				</a>
			<br>
		</td>	
	</tr>	
	<tr>
		<td>		
			<br>
				<s:text name="<b>Art�culo 6.-</b> La escritura constitutiva de una sociedad deber� contener:" /><br />
		</td>	
	</tr>	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name=" ..." /><br />
		</td>	
	</tr>	
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="<b>III.-</b> Su raz�n social o denominaci�n;" /><br />
		</td>	
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="<b> ..." /><br />
		</td>	
	</tr>
	<tr>
		<td class="tituloInteriorRojo">	
			<br class="tituloInteriorRojo"><s:text name="Ejemplo" /><br />
				<img src="<%= request.getContextPath() %>/resources/imgs/plecagris_delgada.jpg"	width="500" height="3" />
			<br>
		</td>	
	</tr>
	<tr>
		<td>		
			<br class="texto_general">
				<s:text name="<b> Denominaci�n:</b> Electr�n, S.A. de C.V." />
			
		</td>	
	</tr>
		<td>		
			<br class="texto_general">
				<s:text name="<b> Raz�n Social:</b> L�pez M�ndez y C�a, S. A. de C.V. " /><br />
		</td>	
	</tr>
</table>
