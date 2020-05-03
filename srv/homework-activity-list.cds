using HomeworkService from './homework-service';

annotate HomeworkService.Activity with @(
	UI: {

		SelectionFields: [ title ],
		LineItem: [
			{Value: title },
			{Value: calculationStrategy.name, Label: '{i18n>calculationStrategy}'},
			{Value: tolerance.name, Label: '{i18n>tolerance}'},
			{Value: createdBy },
			{Value: createdAt }
		]
	}
)
{
	title @UI.HiddenFilter:false;
	title @Common.Label:'{i18n>title}';
};

