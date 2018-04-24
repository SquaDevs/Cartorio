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
<title>Painel Eletronico</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="Menu.jsp" />
	<div id="main" class="container">
		<br> <br>
		<div class="row">
			<div>
				<h1>Painel Eletronico</h1>
				<h3>Projeto Interdisciplinar</h3>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<div>
					<a href="carregar_formulario_senha">Inserir Senha</a><br>
				</div>
				<br>
			</div>
			<div class="form-group col-md-12">
				<div>
					<a href="listar_Atendimento">Atendimentos</a><br>
				</div>
			</div>
			<div class="form-group col-md-12">
				<div>
					<a href="painel">Painel</a><br>
				</div>
			</div>
			<br> <br>
			<div class="form-group col-md-12">
				<div>
					<a href="carregar_formulario_servico">Inserir Serviço</a><br>
					<a href="listar_servicos">Listar Serviços</a>
				</div>
			</div>
			<br>
			<div class="form-group col-md-12">
				<div>
					<a href="carregar_servico_subservico">Inserir Sub Serviço</a><br>
					<a href="listar_subservicos">Listar Sub Serviços</a>
				</div>
			</div>
			<br>
		</div>
		<div class="row">
			<div></div>
		</div>
		<div class="row">
			<div></div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>