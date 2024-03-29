# Calculator android app
Это калькулятор под Андроид. В нем есть только четыре базовых операции, но зато он умеет определять порядок операций. 
### Интерфейс
<img src="/images/calc.jpg" alt="drawing" width="350"/>

### Немного об алгоритме
Как говорилось выше этот калькулятор умеет определять порядок операций. В основе работы лежит синтаксический анализатор на основе стекового метода. 

Зная приоритет операций, составили таблицу отношения предшествования знаков операций:
![тут должен быть скрин таблицы](/images/table.png)

Обозначения:
? – ошибка;  
< - начало основы;  
\> - конец основы;  
= - принадлежат одной основе;  
►- начало выражения;  
◄ - конец выражения.

Отношение < дает операцию переноса. Анализируемые символы преносятся в стек и считываются следующая часть выражения  
Отношение \> дает операцию свертки. Из стека вытаскивается последний элемент, объединяется с анализируемыми символами и выполняется арифметическая операция. После чего результат возвращается в анализируемые символы.   
Отношение = также дает операцию свертки. Но в этой ситуции достаточно только удалить скобки.

#### Для наглядности привожу пример разбора
![тут должен быть скрин примера](/images/example.png)

Суть алгоритма взята из [методических материалов][1] проффесора кафедры ИУ6 МГТУ им. Н.Э. Баумана Ивановой Г.С.


[1]: https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=2ahUKEwiv6JqonKbmAhUy0aYKHUJ1CBgQFjAAegQIBhAC&url=http%3A%2F%2Fe-learning.bmstu.ru%2Fmoodle%2Fpluginfile.php%2F2978%2Fmod_data%2Fcontent%2F778%2Fbmstu_iu6_Sysprogr_Compiles.pdf&usg=AOvVaw3lut6PmoOcp84xGLfDqON3
