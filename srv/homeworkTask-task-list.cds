using HomeworkTaskService from './homeworkTask-service';

annotate HomeworkTaskService.ActivityTask with @(
	UI: {
		SelectionFields: [],
		LineItem: [
			{Value: dueDate, Label: '{i18n>dueDate}'},
			{Value: activity, Label: '{i18n>title}'},
			{$Type: 'UI.DataFieldForAction', Label: '{i18n>markAsDone}', Action: 'HomeworkTaskService.markAsDone'},
		]
	}
)
{
};

annotate HomeworkTaskService.ActivityTask actions {
	markAsDone(
		doneDate @title: '{i18n>doneDate}'
	)
}
