using HomeworkService from './homework-service';

annotate HomeworkService.Activity with @(
	UI: {
		HeaderInfo: {
			TypeName: '{i18n>Activity}',
			TypeNamePlural: '{i18n>Activities}',
			Title: {Value: title},
			//Description: {Value: title}
		},
		Identification: [ //Is the main field group
			{Value: createdBy, Label:'{i18n>CreatedBy}'},
			{Value: createdAt, Label:'{i18n>CreatedAt}'},
			{Value: title },
		],
		HeaderFacets: [
			{$Type: 'UI.ReferenceFacet', Label: '{i18n>interval}', Target: '@UI.FieldGroup#Interval'},
			{$Type: 'UI.ReferenceFacet', Label: '{i18n>calculationStrategy}', Target: '@UI.FieldGroup#CalculationStrategy'},
			{$Type: 'UI.ReferenceFacet', Label: '{i18n>tolerance}', Target: '@UI.FieldGroup#Tolerance'},
			{$Type: 'UI.ReferenceFacet', Label: '{i18n>sad}', Target: '@UI.FieldGroup#Sad'}
		],
		Facets: [
			{$Type: 'UI.ReferenceFacet', Label: '{i18n>OpenTasks}', Target: 'Tasks/@UI.LineItem'},
			{$Type: 'UI.ReferenceFacet', Label: '{i18n>DoneTasks}', Target: 'TasksDone/@UI.LineItem'}
		],
		FieldGroup#Interval: {
			Data: [
				{Value: interval_code}, 
				{Value: interval.name},
				{Value: interval.value},
				{Value: interval.dimension},
			]
		},
		FieldGroup#CalculationStrategy: {
			Data: [
				{Value: calculationStrategy_code},
				{Value: calculationStrategy.name},
			]
		},
		FieldGroup#Tolerance: {
			Data: [
				{Value: tolerance_code},
				{Value: tolerance.name},
			]
		},
		FieldGroup#Sad: {
			Data: [
				{Value: createdBy},
				{Value: createdAt},
				{Value: modifiedBy},
				{Value: modifiedAt},
			]
		}
	}
)
{

};
