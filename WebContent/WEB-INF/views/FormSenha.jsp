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
				<form action="inserir_senha" method="post" th:object="${senha}">
					<br>
					<h1>Senha</h1>
					<br> <br>
					<div class="row">
						<div class="form-group col-md-6">
							<label for="servico">Escolha o Serviço:</label> <select
								class="form-control" name="servico.id">
								<c:forEach var="servico" items="${servicos}">
									<option value="${servico.id}">${servico.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Preferencial?</label>
							<br> 
							<input type="radio" name="preferencial" value="true">Sim
							<br>
							<input type="radio" name="preferencial" value="false" checked>Não
							<br>
						</div>
					</div>
					<br>
					<div id="actions" class="row">
						<div class="col-md-12">
							<button type="submit" class="btn btn-primary" name="acao"
								value="">Gerar</button>
							<a href="index" class="btn btn-danger">Cancelar</a>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>