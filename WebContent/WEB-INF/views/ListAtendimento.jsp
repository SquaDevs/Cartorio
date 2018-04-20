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
			<div class="form-group col-md-10">
				<br> <br>
				<h1>${subServico.servico.nome} - ${subServico.nome}</h1>
				<br>
			</div>
			<div class="form-group col-md-2">
				<br> <br> <a class="btn btn-warning" href="listar_Atendimento">Voltar</a> <br>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<br>
				<h3>Senhas aguardando atendimento</h3>
				<br> <br>
				<table class="table table-bordered table-inverse">
					<thead class="thead-dark">
						<tr>
							<th>Numero</th>
							<th>Preferencial</th>
							<th>Status</th>
							<th>Ações</th>
						</tr>
					</thead>
						<tbody>
							<c:forEach var="senha" items="${senhasParaAtender}">
							<tr>
								<td>${senha.servico.sigla} - ${senha.numero}</td>
								<td>${senha.preferencial}</td>
								<td>${senha.status}</td>
								<td>
									<form action="atender_senha" method="post">
										<input type="hidden" name="idSenha" value="${senha.id}">
										<input type="hidden" name="idSubServico" value="${subServico.id}">
										<button class="btn btn-success" type="submit">Atender</button>
									</form>
								</td>
							</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<br> <br>
				<h3>Senhas em atendimento</h3>
				<br> <br>
				<table class="table table-bordered table-inverse">
					<thead class="thead-dark">
						<tr>
							<th>Numero</th>
							<th>Preferencial</th>
							<th>Status</th>
							<th>Ações</th>
						</tr>
					</thead>
						<tbody>
							<c:forEach var="senha" items="${senhasEmAtendimento}">
							<tr>
								<td>${senha.servico.sigla} - ${senha.numero}</td>
								<td>${senha.preferencial}</td>
								<td>${senha.status}</td>
								<td>
									<form action="finalizar_senha" method="post">
										<input type="hidden" name="idSenha" value="${senha.id}">
										<input type="hidden" name="idSubServico" value="${subServico.id}">
										<button class="btn btn-danger" type="submit">Finalizar</button>
									</form>
								</td>
							</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>