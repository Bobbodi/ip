# Bobbodi User Guide
Bello! This is a minion-themed chatbot to help you organise your tasks.

<img width="374" height="545" alt="Ui" src="https://github.com/user-attachments/assets/29e38a1d-6c71-4fcc-8212-baa2f2f9906a" />

## Creating tasks 
One can create three types of tasks: todo, deadline, event

### `todo [task description]`
Add a task with a description  
Example: `todo homework`

### `deadline [task description] /by [date]`
Add a task with a description and a deadline  
The date has to have a correct date format  
Example: `deadline homework /by 9/17/2050`

### `event [task description] /from [date] /to [date]`
Add an event that spans multiple days  
Example: `event party /from 9/17/2050 /to 9/18/2050`

## Managing tasks 
One can manage tasks in multiple ways 

### `list` 
View all tasks  
Example: `list`

### `find [keyword]`
Find a task whose task description contains [keyword]  
Example: `find homework help`

### `mark [task number]` 
Mark a task as done  
Task number follows numbering in the list of tasks. Starts from 1.  
Example: `mark 2`

### `unmark [task number]` 
Mark a task as not done  
Example: `unmark 2`

### `delete [task number]` 
Delete a task  
Example: `unmark 2`

### `due [date]` 
Show tasks that are due on [date]
Example: `due 7/8/2050`

### `reminder [days]` 
Show tasks that will be due in [days] days
Example: `reminder 2` shows tasks due in 2 days

### `load [filename]` 
Add tasks in a file
Example: file `C:\Documents\data.txt` contains the following text  
T - 1 - read book  
D - 0 - return book - 6 Jun 2025  
E - 0 - project meeting - 27-Aug-2025 - 28/8/2025  
T - 1 - join sports club  
`load C:\Documents\data.txt`

### `help` 
See list of commands 

### `bye`
Close the application
