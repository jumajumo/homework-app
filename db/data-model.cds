namespace de.jumajumo.homework;

using { sap.common.CodeList, cuid, managed } from '@sap/cds/common';

type CalculationStrategy: Association to CalculationStrategyData;
type Interval: Association to IntervalData;
type Tolerance: Association to ToleranceData;

entity CalculationStrategyData: CodeList {
  key code: String(3) @title: '{i18n>calculationStrategyCode}';
}

entity IntervalData: CodeList {
  key code: String(3) @title: '{i18n>intervalCode}';
  value: Integer @title:'{i18n>intervalValue}';
  dimension: String enum {DAY;WEEK;MONTH;YEAR};
}

annotate IntervalData.dimension @(title:'{i18n>intervalDimension}');

entity ToleranceData: CodeList {
  key code: String(3) @title: '{i18n>toleranceCode}';
}

@fiori.draft.enabled
entity Activity: cuid,managed {
  title  : String;
  calculationStrategy : CalculationStrategy default 1;
  interval: Interval default 1;
  tolerance: Tolerance default 7;
  Tasks : Composition of many ActivityTask on Tasks.parent=$self;
  TasksDone : Composition of many ActivityTaskDone on TasksDone.parent=$self;
}

entity ActivityTask: cuid {
  parent : Association to Activity;
  dueDate: Date;
}

annotate ActivityTask with {
  dueDate @mandatory;
}

entity ActivityTaskDone: cuid {
  parent : Association to Activity;
  dueDate: Date;
  doneDate: Date;
}

annotate ActivityTaskDone with {
  dueDate @mandatory;
  doneDate @mandatory;
}


