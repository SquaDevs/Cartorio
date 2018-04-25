<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="Menu.jsp" />
	<div id="main" class="container">

		<div class="row">
			<div class="form-group col-md-12">
				<div class="row">
					<label>Servico: </label>
					<input disabled="disabled" name="servico" value="${senha.servico.nome}"/>
				</div>
				<div class="row">
					<label>Preferencial: </label>
					<input disabled="disabled" type="text" name="preferencial" value="${senha.preferencial}"/>
				</div>
				<div class="row">
					<label>Numero: </label>
					<input disabled="disabled" type="text" name="numero" value="${senha.numero}"/>
					<label>Cadastrado em: </label>
					<input disabled="disabled" type="text" name="inicio" value="${senha.data_inicio}"/>
				</div>
				<div class="row">
					<label>Previsão de inicio: </label>
					<label>${previsaoInicio}</label>
				</div>
				<div class="row">
					<label>Previsão de termino: </label>
					<label>${previsaoFim}</label>
				</div>
				<br><br>
				<div class="row">
					<a href="painel">Consultar Painel de Atendimento</a>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>