# Imports-task
Задан формат файлов .vars:

file1.vars

<br/>a = 10
<br/>b = 15
<br/>c = a
<br/>кроме декларации переменных есть поддержка импорта из других файлов, в этом случае можно будет обращаться к переменным, заданным в импортированном файле:

file2.vars
<br/>import file1
<br/>d = 40
<br/>e = a
<br/>foo = c
<br/>как вы можете заметить в file2.vars использовались переменные a и b из файла file1.vars.

Файл file2.vars можно импортировать в другом файле итд:

file3.vars

<br/>import file2
<br/>a3 = e
<br/>b3 = d
<br/>foo3 = foo
<br/>Являются ли импорты транзитивными, то есть доступны ли переменные a, b, c в файле `file3.vars - остается на ваше усмотрение.

В файле может быть больше одного импорта.

Задача написать на языке Scala приложение, которое при передаче ему на вход имени файла будет выводить значения всех объявленных в нем переменных, или ошибку, если что-то пошло не так.

Ошибки могут быть связаны с использованием нигде не объявленных переменных, конфликтах в объявлении переменных, циклических зависимостях и т.д.

Важно чтобы в случае ошибки выводилось понятное пользователю сообщение о том в каком файле и в чем ошибка.
