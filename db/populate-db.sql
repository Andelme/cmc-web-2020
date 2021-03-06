SET DATESTYLE TO "DMY";

INSERT INTO DEPARTMENT (department_name, head_department) VALUES
('Andelme Headquarters', NULL),
('Andelme Research', 1),
('Andelme Mechanics', 1),
('Отдел кадров', 1),
('Финансовый отдел', 1),
('Плановый отдел', 1),
('Отдел научных исследований', 2),
('Производственный отдел', 3);

INSERT INTO POSITION_TYPE (postype_name, responsibilities, salary) VALUES
('Начальник отдела кадров', 'Возглавляет работу по набору и распределению работников по рабочим местам, ' ||
                               'Проводит анализ кадровой работы в компании, разрабатывает пути её улучшения, ' ||
                               'Руководит работниками отдела, ' ||
                               'Разрабатывает мероприятия по укреплению трудовой дисчиплины, снижению текучести кадров', 160000),
('Начальник финансового отдела', 'Организует управление движением финансовых ресурсов предприятия и регулирование финансовых отношений, ' ||
                                    'возникающих между хозяйствующими субъектами в условиях рынка, в целях наиболее эффективного использования ' ||
                                    'всех видов ресурсов в процессе производства и реализации продукции (работ, услуг) и получения максимальной прибыли, ' ||
                                    'Руководит разработкой проектов перспективных и текущих финансовых планов, ' ||
                                    'Руководит работниками отдела.', 160000),
('Бухгалтер', 'Ведёт первичный бухгалтерский учёт, ' ||
                 'производит расчёт, начисление, а также перечисление налогов по отчётным периодам, ' ||
                 'Осуществляет выплаты работникам', 60000),
('Начальник планового отдела', 'Организует контроль за выполнением подразделениями предприятия плановых заданий, ' ||
                                  'а также статистический учет по всем производственным и технико-экономическим показателям работы предприятия, ' ||
                                  'подготовку периодической отчетности в установленные сроки, систематизацию статистических материалов, ' ||
                                  'Обеспечивает подготовку заключений на проекты оптовых цен на продукцию, поставляемую предприятию, ' ||
                                  'Обеспечивает доведение плановых заданий до подразделений предприятия, ' ||
                                  'Руководит работниками отдела', 160000),
('Финансовый аналитик', 'Анализирует деятельность организации, ' ||
                           'Осуществляет финансовые исследования, ' ||
                           'Собирает данные для финансовых отчетов, ' ||
                           'Анализирует целесообразность заключения договоров', 90000),
('Лаборант', 'Следит за исправным состоянием лабораторного оборудования, осуществляет его наладку, ' ||
                'Выполняет лабораторные анализы, испытания, измерения и другие виды работ при проведении исследований и разработок, ' ||
                'Обрабатывает, систематизирует и оформляет в соответствии с методическими документами результаты анализов, испытаний, измерений, ведет их учет, ' ||
                'Обеспечивает сотрудников подразделения необходимыми для работы оборудованием, материалами, реактивами и др', 10000),
('Руководитель исследовательского отдела', 'Разрабатывает проекты перспективных и годовых планов работы подразделения и представляет их руководству, ' ||
                                              'Осуществляет научное руководство по проблемам, предусмотренным в тематическом плане подразделения, ' ||
                                              'формулирует их конечные цели и предполагаемые результаты и принимает непосредственное участие в проведении важнейших работ, ' ||
                                              'Организует работу по патентованию и лицензированию научных и технических достижений, регистрации изобретений и рационализаторских предложений, ' ||
                                              'Следит за безопасным проведением работ, соблюдением правил и норм охраны труда, ' ||
                                              'Руководит работниками отдела', 160000),
('Сотрудник исследовательской лаборатории', 'обосновывает направления новых исследований и разработок и методы их выполнения, ' ||
                                               'вносит предложения для включения в планы научно-исследовательских работ, ' ||
                                               'Проводит лабораторные исследования в рамках текущего проекта, ' ||
                                               'Внедряет результаты проведённых исследований и разработок', 80000),
('Руководитель отдела производства', 'Организует техническую подготовку производства, ' ||
                                        'Осуществляет руководство текущим и перспективным планированием технического развития предприятия, его производственной базы, ' ||
                                        'Контролирует производство продукции в соответствии с заказами, ' ||
                                        'Организует техническое обслуживания и ремонт оборудования, ' ||
                                        'Руководит работниками отдела', 140000),
('Инженер-конструктор', 'Разрабатывает эскизные, технические и рабочие проекты особо сложных, сложных и средней сложности изделий, ' ||
                            'используя средства автоматизации проектирования, передовой опыт конкурентно способных изделий, ' ||
                            'обеспечивает при этом соответствие разрабатываемых конструкций техническим заданиям, стандартам ' ||
                            'Проводит патентные исследования и определяет показатели технического уровня проектируемых изделий, ' ||
                            'Участвует в монтаже, наладке, испытаниях и сдаче в эксплуатацию опытных образцов изделий, узлов, ' ||
                            'систем и деталей новых и модернизированных конструкций, выпускаемой предприятием продукции, ' ||
                            'в составлении заявок на изобретения и промышленные образцы, а также в работах по совершенствованию модернизации, ' ||
                            'унификации конструируемых изделий, их элементов и в разработке проектов стандартов и сертификатов', 40000);

INSERT INTO WORKER (name, birth_date, address, phone_number, hire_date, education_degree) VALUES
('Афанасьева Екатерина Сергеевна', '23-03-1980', 'Москва, ул.Леваневского, д.77, кв.59', '+7(965)874-04-31', '2005-01-05', 'doctor'),
('Иванов Алексей Дмитриевич', '19-01-1976', 'Москва, ул.Трехгорный Вал, д.32, кв.86', '+7(965)640-03-02', '2005-02-21', 'doctor'),
('Тетерев Олег Витальевич', '01-05-1995', 'Москва, ул.Взлетная, д.50, кв.69', '+7(965)112-55-43', '2018-04-23', 'without_degree'),
('Добролюбов Пётр Андреевич', '02-03-1972', 'Москва, ул.Дальняя, д.85, кв.44', '+7(965)765-83-44', '2006-03-18', 'master'),
('Царёва Анна Сергеевна', '14-04-1982', 'Москва, ул.Трехгорный Вал, д.5, кв.73', '+7(965)333-56-32', '2010-04-17', 'master'),
('Петров Александр Васильевич', '14-11-1999', 'Москва, ул.Рабочая, д.96, кв.83', '+7(965)125-65-35', '2019-11-14', 'bachelor'),
('Петренко Тамара Ивановна', '14-07-1977', 'Москва, ул.Трехгорный Вал, д.54', '+7(965)563-34-64', '2005-02-03', 'doctor'),
('Дягтерёв Василий Алекссевич', '24-11-1984', 'Москва, ул.Бакинская, д.55, кв.33', '+7(965)124-65-32', '2017-04-18', 'master'),
('Гончаров Сергей Рудольфович', '14-12-1979', 'Москва, ул.Салтыковская, д.91, кв.86', '+7(965)563-23-67', '2007-03-02', 'doctor'),
('Тычкин Сергей Игоревич', '21-11-1988', 'Москва, ул.Вересковая, д.11, кв.85', '+7(965)272-14-54', '2014-07-18', 'without_degree');

INSERT INTO WORK_POSITION (worker_id, postype_id,  appointment_date, retire_date, work_rate, department_id) VALUES
(1, 1, '05-01-2005', NULL, 1, 1),
(2, 2, '21-02-2005', NULL, 1, 2),
(3, 3, '23-04-2018', NULL, 1, 2),
(4, 4, '18-03-2006', NULL, 1, 3),
(5, 5, '17-04-2010', NULL, 1, 3),
(6, 6, '14-11-2019', NULL, 0.5, 4),
(7, 6, '03-02-2005', '12-04-2007', 0.5, 4),
(7, 8, '12-04-2007', '01-03-2009', 1, 4),
(7, 7, '01-03-2009', NULL, 1, 4),
(8, 8, '18-04-2017', NULL, 1, 4),
(9, 9, '02-03-2007', NULL, 1, 5),
(10, 10, '18-07-2014', NULL, 0.5, 5),
(NULL, 6, NULL, NULL, 0.5, 4),
(NULL, 6, NULL, NULL, 0.5, 4),
(NULL, 5, NULL, NULL, 1, 3),
(NULL, 8, NULL, NULL, 1, 4),
(NULL, 10, NULL, NULL, 1, 5);