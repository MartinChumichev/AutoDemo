Feature: TextBox

  Scenario Template: Заполнение текстовой формы

    Given Открыть страницу: "text-box"
    When Заполнить форму пользователя: "<Name>", "<Email>", "<CurrentAddress>", "<PermanentAddress>"
    Then Проверить, что output форма содержит: "<Name>, <Email>, <CurrentAddress>, <PermanentAddress>"
    Examples:
      | Name  | Email       | CurrentAddress | PermanentAddress |
      | Vasya | Vasya@ya.ru | Moscow         | Wocsom           |