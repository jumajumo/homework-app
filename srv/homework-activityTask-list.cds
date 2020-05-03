using HomeworkService from './homework-service';

annotate HomeworkService.ActivityTask with @(
	UI: {
		HeaderInfo: {
			TypeName: '{i18n>Task}',
			TypeNamePlural: '{i18n>Tasks}'
		},
		////////////////////////////////////////////////////////////////////////////
		//
		//	Lists of ActivityTasks
		//
		SelectionFields: [ ],
		LineItem: [
			{Value: dueDate },
			{$Type: 'UI.DataFieldForAction', Label: '{i18n>markAsDone}', Action: 'HomeworkService.markAsDone'},
		]
	}
)
{
	dueDate @Common.Label:'{i18n>dueDate}';
};

annotate HomeworkService.ActivityTask actions {
	markAsDone(
		doneDate @title: '{i18n>doneDate}'
	)
}

annotate HomeworkService.ActivityTaskDone with @(
	UI: {
		HeaderInfo: {
			TypeName: '{i18n>Task}',
			TypeNamePlural: '{i18n>Tasks}'
		},
		////////////////////////////////////////////////////////////////////////////
		//
		//	Lists of ActivityTasks
		//
		SelectionFields: [ isDone ],
		LineItem: [
			{Value: dueDate },
			{Value: doneDate },
			{$Type: 'UI.DataFieldForAction', Label: '{i18n>reopen}', Action: 'HomeworkService.reopen'},
		]
	}
)
{
	dueDate @Common.Label:'{i18n>dueDate}';
	doneDate @Common.Label:'{i18n>doneDate}';
};


annotate HomeworkService.ActivityTaskDone actions {
	reopen(
		nop @title: 'nop'
	)
}
