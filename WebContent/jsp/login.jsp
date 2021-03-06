<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="container">
			<div class="col-md-4 col-md-offset-4">
				<div class="space"></div>
				<div class="text-center">
					<img src="/img/icon_white.png" class="header-image"/>
				</div>
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="text-center">
							<h3>Login</h3>
						</div>
						<form method="POST" action="">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
									<input type="text" class="form-control" id="account" name="account" placeholder="User" autofocus>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
									<input type="password" class="form-control" id="passwd" name="passwd" placeholder="Password">
								</div>
							</div>
							<button type="submit" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-log-in"></span> Sign In</button>
							<button type="button" data-toggle="modal" data-target="#registerModal" class="btn btn-default btn-block"><span class="glyphicon glyphicon-pencil"></span> Register</button>
						</form>
					</div>
				</div>
				<div class="well text-center">
					Background image generated with <a target="_blank" href="http://app.geokone.net">GeoKone</a>
				</div>
				<c:choose>
					<c:when test="${ param.acc == null }">
						<!-- Empty -->
					</c:when>
					<c:when test="${ param.acc == 1 }">
						<div class="alert alert-success">
							<strong>Congratulations!!</strong> Your account has been created
						</div>
					</c:when>
					<c:when test="${ param.acc == 0 }">
						<div class="alert alert-danger">
							<strong>Sorry :(</strong> There was an error creating your account, try again
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
		
		<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="registerModalLabel">Create an account</h4>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<div class="well">
										There's just a few things you need to do
										and you'll have your own account, provide a
										nickname ( As original as you can, we'll check for availability )
										password and an email account ( Needed to get your Gravatar ).
									</div>
								</div>
								<div class="col-md-12">
									<form method="POST" action="/shadowsatyr/createaccount" id="registerForm">
										<div class="form-group" id="nicknamec">
											<label class="control-label" for="nickname">Nickname</label>
											<input maxlength="25" data-container="body" data-toggle="popover" data-trigger="manual" data-placement="top" data-content="Nickname not available" oninput="validateNickname( )" type="text" class="form-control" id="nickname" name="nickname" placeholder="Nickname" required>
										</div>
										<div class="form-group" id="emailc">
											<label class="control-label" for="email">Email address</label>
											<input maxlength="255" data-container="body" data-toggle="popover" data-trigger="manual" data-placement="top" data-content="Mail account already registered" oninput="validateMail( )" type="email" class="form-control" id="email" name="email" placeholder="Email" required>
										</div>
										<div class="form-group">
											<label for="passwdr" class="control-label">Password</label>
											<input type="password" class="form-control" id="passwdr" name="passwdr" placeholder="Password" required>
										</div>
										<div class="form-group" id="passwdc">
											<label class="control-label" for="passwdcon">Confirm Password</label>
											<input data-container="body" data-toggle="popover" data-trigger="manual" data-placement="top" data-content="Passwords don't match" oninput="validatePasswd( )" type="password" class="form-control" id="passwdcon" name="passwdcon" placeholder="Password" required>
										</div>
										<button type="submit" class="hide" id="submitButton" name="submitButton"></button>
										<input type="hidden" id="validMail" name="validMail" value="false" />
										<input type="hidden" id="validNickname" name="validNickname" value="false" />
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" onclick="sendSubmit( )">Register</button>
					</div>
				</div>
			</div>
		</div>