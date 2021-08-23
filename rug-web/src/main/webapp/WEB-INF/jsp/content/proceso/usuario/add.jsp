<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="section"></div>
<main>
    <center>
        <div class="container">
            <div class="row">
                <div class="col s12">
                    <div class="card">
                        <div class="card-content">
                            <span class="card-title">
                                
                                <s:text name="usuario.titulo.registro"/>
                            </span>
                            <div class="row">
                                <div class="col m2"></div>
                                <s:form namespace="usuario" action="save.do" theme="simple" cssClass="col s12 m8" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col s12 action-error">
                                            <s:actionerror />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <select name="personaFisica.tipoPersona" id="tipoPersona" onchange="onChangeTipoPersona()">
                                                <option value="PF">Persona Individual</option>
                                                <option value="PM">Persona Jurídica</option>
                                            </select>
                                            <label for="tipoPersona"><s:text name="usuario.field.tipoPersona" /></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <select name="personaFisica.nacionalidadInscrito" id="nacionalidadInscrito" onchange="onChangeTipoPersona()">
                                                <option value="N">Nacional</option>
                                                <option value="E">Extranjero</option>
                                            </select>
                                            <label for="personaFisica.tipoDocumento"><s:text name="usuario.field.nacionalidadInscrito" /></label>
                                        </div>
                                    </div>
                                    <div class="user-data">
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <s:textfield name="personaFisica.nombre" id="nombre" required="true" cssClass="validate" maxlength="150" size="150" />
                                                <label for="nombre"><s:text name="usuario.field.nombre" /></label>
                                            </div>
                                            <s:fielderror fieldName="personaFisica.nombre" />
                                        </div>
                                        <div class="row" id="nit">
                                            <div class="input-field col s12">
                                                <s:textfield name="personaFisica.rfc" id="nit" cssClass="validate" maxlength="25" size="40" onkeypress="return aceptaalfa(event);"/>
                                                <label for="nit"><s:text name="usuario.field.nit" /></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s4">
                                                <s:textfield name="personaFisica.documentoIdentificacion" id="documentoIdentificacion" cssClass="validate" maxlength="13" size="40" onkeyup="this.value = this.value.toUpperCase()" onkeypress="return aceptaalfa(event);" />
                                                <label for="documentoIdentificacion"><s:text name="usuario.field.documentoIdentificacion" /></label>
                                            </div>
                                            <div class="file-field input-field col s8">
                                                <div class="btn">
                                                    <span>Adjuntar documento</span>
                                                    <input type="file" name="upload" id="uploadfile" />										
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate" type="text" placeholder="Selecciona un archivo...">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">							    	
                                            <p>							    		
                                                <input type="checkbox" name="registroUsuario.judicial" id="registroUsuario.judicial" value="true" />
                                                <label for="registroUsuario.judicial"><s:text name="usuario.field.judicial" /></label>
                                            </p>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <s:textfield name="personaFisica.datosContacto.emailPersonal" id="personaFisica.datosContacto.emailPersonal" required="true" cssClass="validate" maxlength="256" size="40" onkeyup="this.value = this.value.toLowerCase()" />
                                                <label for="personaFisica.datosContacto.emailPersonal"><s:text name="usuario.field.correo" /></label>
                                            </div>
                                            <s:fielderror fieldName="personaFisica.datosContacto.emailPersonal" />
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <s:text name="usuario.field.password.ayuda" var="password.ayuda" />
                                                <s:password name="registroUsuario.password" id="registroUsuario.password" required="true" cssClass="validate tooltipped" data-position="right" data-delay="50" data-tooltip="%{password.ayuda}" maxlength="16" size="40" />
                                                <label for="registroUsuario.password"><s:text name="usuario.field.password" /></label>
                                            </div>
                                            <s:fielderror fieldName="registroUsuario.password" />
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <s:password name="registroUsuario.confirmacion" id="registroUsuario.confirmacion" required="true" cssClass="validate" maxlength="16" size="40" />
                                                <label for="registroUsuario.confirmacion"><s:text name="usuario.field.confirmacion" /></label>
                                            </div>
                                            <s:fielderror fieldName="registroUsuario.confirmacion" />
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <s:select name="registroUsuario.pregunta" id="pregunta" list="preguntas" listKey="pregunta" listValue="pregunta" onchange="onChangePregunta()" />
                                                <label for="pregunta"><s:text name="usuario.field.pregunta" /></label>
                                            </div>
                                        </div>
                                        <div class="row" id="otra-pregunta" style="display: none;">
                                            <div class="input-field col s12">
                                                <s:textfield name="otraPregunta" id="otraPregunta" cssClass="validate" maxlength="50" size="40" />
                                                <label for="otraPregunta">Ingrese la pregunta</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12">
                                                <s:textfield name="registroUsuario.respuesta" id="registroUsuario.respuesta" required="true" cssClass="validate" maxlength="50" size="40" />
                                                <label for="registroUsuario.respuesta"><s:text name="usuario.field.respuesta" /></label>
                                            </div>
                                            <s:fielderror fieldName="registroUsuario.respuesta" />
                                        </div>
                                        <div class="row">
                                            <div class="input-field col s12 m4">
                                                <img id="captchaImage" src="<%=request.getContextPath() %>/captcha.jp">
                                            </div>
                                            <div class="input-field col s12 m4">
                                                <a class="waves-effect waves-light btn" onclick="newCapcha();"><i class="material-icons">autorenew</i></a>
                                            </div>
                                            <div class="input-field col s12 m4">
                                                <s:textfield name="captchaKey" id="captchaKey" required="true" cssClass="validate" />
                                                <label for="captchaKey"><s:text name="usuario.field.captcha" /></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <p>
                                                <input type="checkbox" name="registroUsuario.terminos" value="true" id="registroUsuario.terminos"/>
                                                <label for="registroUsuario.terminos" class="checkboxLabel">
                                                    <s:text name="usuario.field.leido" />&nbsp;														
                                                    <s:url action="help.do" encode="true" namespace="/comun/publico" var="urlTerminos">
                                                        <s:param name="llave">terminosyCondicionesdeUso</s:param>
                                                        <s:param name="keepThis">true</s:param>
                                                        <s:param name="TB_iframe">true</s:param>
                                                        <s:param name="height">500</s:param>
                                                        <s:param name="width">750</s:param>
                                                    </s:url> 																					
                                                    <s:a href="%{urlTerminos}" cssClass="content-modal" data-title="Términos y condiciones">
                                                        <s:text name="common.terminos" />							
                                                    </s:a>
                                                </label>
                                            </p>
                                            <s:fielderror fieldName="registroUsuario.terminos" />
                                        </div>
                                        <div class="row">
                                            <div class="col s12">
                                                <p><s:text name="usuario.add.aviso" /></p>
                                            </div>
                                        </div>
                                    </div>
                                    <center>
                                        <div class='row'>
                                            <s:url value="%{getContextPath()}/imgs/%{getText('common.img.i18n.path')}/aceptar.gif" var="urlAceptar"/>
                                            <s:submit type="button" src="%{urlAceptar}" name="Aceptar" value="Aceptar" align="center" cssClass="btn btn-large waves-effect indigo" />
                                        </div>
                                    </center>
                                </s:form>
                                <div class="col m2"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </center>
</main>
<div class="section"></div>
<div id="contentModal" class="modal">
    <div class="modal-content"></div>
    <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cerrar</a>
    </div>
</div>

<s:actionmessage />

<script type="text/javascript">
    var idImage = 0;
    function newCapcha() {
        getObject('captchaImage').src = "<%=request.getContextPath() %>/captcha.jp?id=" + idImage;
        idImage = idImage + 1;
    }
</script>
