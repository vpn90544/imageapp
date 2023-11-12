# imageapp
В приложении две вкладки:
1) рандомные картинки
2) любимые картинки

На 1 вкладке по мере скрола погружаются новые пикчи и их можно лайкать, подгрузка автоматическая с пагинацией.
На 2 вкладке лежат лайкнутые пикчи. Лайкнутые так же должны быть доступны для просмотра без интернета.

![test00](https://github.com/vpn90544/imageapp/assets/150622435/4cb0f55a-d367-487c-83cc-b53f699d5c92)
![test2](https://github.com/vpn90544/imageapp/assets/150622435/6c27bb47-c603-4827-aeb9-cb67396ac019)

  MVVM+(частичное MVI в виде единого состояния экрана),
  для di - выбрал dagger (возможно излишняя многомодульность для данного проекта ,но на практике чаще используется даггер, в связи с размерами проекта),
  для навигации - кастомное решение (экстеншены над фрагмент менеджером). (также создание фиче модуля и ее api модуля для избежания циклической зависимости - стандартное решение),
  coroutines,flow,
  кастомный делегат адаптер (base,composite,кто как называет) написанный мной , более простой вариант (есть более сложный под SDUI),
  пагинация - экстеншен над ресайклером,
  clean архитектура - (видение ее гугла "без домайн слоя",тут уже холивар) для меня суть в не в количестве модулей (в исходной схеме 5-6) , а в отношении между ними .
  Тесты увы не написал (не хватает времени),использую на практике kotest и mockk , есть опыт написания тестов на кастомные вьюшки(логика выносится в отдельный класс и проверяем что вызывается через этот класс у вьюшки)
  так стандартно вьюмодель, реопзиторий не менее 80%.
