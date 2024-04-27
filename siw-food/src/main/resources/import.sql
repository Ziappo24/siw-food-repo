
insert into ricetta (id, nome, url_Image, descrizione) values(nextval('ricetta_seq'), 'Carbonara', 'https://foodwhirl.com/wp-content/uploads/2017/02/spaghetti-carbonara-insta-768x762.jpg', 'La Carbonara, iconico piatto italiano, è una pasta cremosa e succulenta arricchita con uova, guanciale o pancetta croccante, pecorino romano e pepe nero. La sua salsa vellutata si ottiene mescolando uova crude con formaggio e pepe, creando una deliziosa armonia di sapori. Servita su spaghetti o rigatoni al dente, questa prelibatezza culinaria è un ode alla tradizione romana, offrendo un esperienza culinaria irresistibile e ricca di gusto.');
insert into ricetta (id, nome, url_Image, descrizione) values(nextval('ricetta_seq'), 'Amatriciana', 'https://www.ntacalabria.it/wp-content/uploads/2015/10/piatto_di_vera_amatriciana-1024x681.jpg', 'Un classico piatto italiano, l amatriciana è un condimento per la pasta composto da guanciale, pomodoro, pecorino romano e peperoncino. Il guanciale viene saltato in padella con olio d oliva e peperoncino per creare un sapore ricco e speziato, mentre il pomodoro aggiunge dolcezza e acidità al piatto. Il tutto viene poi amalgamato con abbondante pecorino romano per ottenere una salsa cremosa e gustosa. Servita con pasta rigata, l amatriciana è un esplosione di sapori tradizionali romani.');
insert into ricetta (id, nome, url_Image, descrizione) values(nextval('ricetta_seq'), 'Cacio e Pepe', 'https://i0.wp.com/www.thegrguide.com/wp-content/uploads/2019/11/Cacio-e-Pepe-Dinner.jpg?ssl=1', 'Un piatto semplice ma delizioso, la cacio e pepe è una pasta romana che mette in evidenza la bellezza dei sapori semplici. Preparata con solo tre ingredienti principali - pecorino romano, pepe nero e pasta - questa pietanza è un esempio classico della cucina italiana. Il pecorino romano viene fuso con un po  d acqua di cottura della pasta per creare una salsa cremosa e saporita, mentre il pepe nero aggiunge un tocco di calore e spezie. Servita con pasta spaghetto o tonnarelli, la cacio e pepe è un esperienza culinaria genuina e appagante.');

insert into cuoco (id, nome, cognome, url_Image, nascita) values(nextval('cuoco_seq'), 'Bruno', 'Barbieri', 'https://www.occhionotizie.it/wp-content/uploads/2019/01/Bruno-Barbieri.jpg','1958-08-25');
insert into cuoco (id, nome, cognome, url_Image, nascita) values(nextval('cuoco_seq'), 'Antonino', 'Cannavacciuolo', 'https://www.salepepe.it/cdn-cgi/image/width=992/files/2014/04/FOXLIFE_CucineDaIncubo_seconda-stagione_Cannavacciuolo-1.jpg','1990-10-13');
insert into cuoco (id, nome, cognome, url_Image, nascita) values(nextval('cuoco_seq'), 'Vincenzo', 'Milillo', 'https://th.bing.com/th/id/OIP.EfNjLZJsFSubH_ESUaqUVQHaHa?rs=1&pid=ImgDetMain','2002-09-08');