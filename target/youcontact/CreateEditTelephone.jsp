<html>
	<head>
		<link href="files/css/style.css" rel="stylesheet">
	</head>
	<body>
        <h1>Создать или редактировать телефон.</h1>
		<form>
			<label>Код страны:</label>
			<input type="text" placeholder="код страны"/>
			<label>Код оператора:</label>
			<input type="text" placeholder="код оператора"/>
			<label>Телефонный номер:</label>
			<input type="text" placeholder="телефонный номер"/>
			<fieldset>
				<legend>Тип телефона</legend>
				<label class="tel">Домашний</label>
				<input class="tel" type="radio" name="telephone_type"/>
				<label class="tel">Мобильный</label>
				<input type="radio" class="tel" name="telephone_type"/>
			</fieldset>
			<label>Комментарий:</label>
			<input type="text" placeholder="комментарий"/>
			<button><a href="?command=SaveTelephoneCommand">Сохранить</a></button>
			<button><a href="javascript:window.close()">Отменить</a></button>
		</form>
	</body>
</html>