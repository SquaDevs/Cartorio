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
				<br> <br>
				<h3>Painel de Atendimento</h3>
				<br> <br>
				<table class="table table-bordered table-inverse">
					<thead class="thead-dark">
						<tr>
							<th>Senha Numero</th>
							<th>Tipo de Serviço</th>
							<th>Status</th>
							<th>Tempo Medio</th>
							<th>Horario de Inicio</th>
							<th>Aguardando a</th>
						</tr>
					</thead>
						<tbody>
							<c:forEach var="senha" items="${senhas}">
							<tr>
								<td>${senha.servico.sigla} ${senha.numero}</td>
								<td>${senha.servico.nome}</td>
								<td>${senha.status}</td>
								<td>--</td>
								<td>--</td>
								<td>--</td>
							</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>