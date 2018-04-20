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
	<form action="listar_atendimento_senha" method="post" >
		<div id="main" class="container">
			<div class="row">
				<div class="form-group col-md-12">
					<br>
					<h1>Atendimento</h1>
					<br> <br>
				</div>
				<div class="form-group col-md-6">
					<label for="servico">Escolha o Serviço e o Sub Serviço para atender:</label> 
					<br>
					<select class="form-control" name="id">
						<c:forEach var="subServico" items="${subservicos}">
							<option value="${subServico.id}">${subServico.servico.nome}
								- ${subServico.nome}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<br>
			<div id="actions" class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="acao" value="">Listar
						Senhas</button>
					<a href="index" class="btn btn-danger">Cancelar</a>
				</div>
			</div>
		</div>
	</form>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>