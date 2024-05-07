
insert into ricetta (id, nome, url_Image, descrizione) values(nextval('ricetta_seq'), 'Carbonara', 'Espaguetis_carbonara.jpg', 'La Carbonara, iconico piatto italiano, è una pasta cremosa e succulenta arricchita con uova, guanciale o pancetta croccante, pecorino romano e pepe nero. La sua salsa vellutata si ottiene mescolando uova crude con formaggio e pepe, creando una deliziosa armonia di sapori. Servita su spaghetti o rigatoni al dente, questa prelibatezza culinaria è un ode alla tradizione romana, offrendo un esperienza culinaria irresistibile e ricca di gusto.');
insert into ricetta (id, nome, url_Image, descrizione) values(nextval('ricetta_seq'), 'Amatriciana', 'amatriciana.png', 'Un classico piatto italiano, l amatriciana è un condimento per la pasta composto da guanciale, pomodoro, pecorino romano e peperoncino. Il guanciale viene saltato in padella con olio d oliva e peperoncino per creare un sapore ricco e speziato, mentre il pomodoro aggiunge dolcezza e acidità al piatto. Il tutto viene poi amalgamato con abbondante pecorino romano per ottenere una salsa cremosa e gustosa. Servita con pasta rigata, l amatriciana è un esplosione di sapori tradizionali romani.');
insert into ricetta (id, nome, url_Image, descrizione) values(nextval('ricetta_seq'), 'Cacio e Pepe', 'cacio_e_pepe.png', 'Un piatto semplice ma delizioso, la cacio e pepe è una pasta romana che mette in evidenza la bellezza dei sapori semplici. Preparata con solo tre ingredienti principali - pecorino romano, pepe nero e pasta - questa pietanza è un esempio classico della cucina italiana. Il pecorino romano viene fuso con un po  d acqua di cottura della pasta per creare una salsa cremosa e saporita, mentre il pepe nero aggiunge un tocco di calore e spezie. Servita con pasta spaghetto o tonnarelli, la cacio e pepe è un esperienza culinaria genuina e appagante.');

insert into cuoco (id, nome, cognome, url_Image, nascita) values(nextval('cuoco_seq'), 'Bruno', 'Barbieri', 'BrunoBarbieri.png','1958-08-25');
insert into cuoco (id, nome, cognome, url_Image, nascita) values(nextval('cuoco_seq'), 'Antonino', 'Cannavacciuolo', 'Antonio-Cannavacciuolo.png','1990-10-13');
insert into cuoco (id, nome, cognome, url_Image, nascita) values(nextval('cuoco_seq'), 'Massimiliano', 'Mariola', 'max_mariola.png','1969-04-11');
insert into cuoco (id, nome, cognome, url_Image, nascita) values(nextval('cuoco_seq'), 'Giorgio', 'Barchiesi', 'giorgione.png','1957-06-07');

insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Uova', '0', 'uova.png','null');
insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Guanciale', '0', 'guanciale.png','null');
insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Pecorino', '0', 'pecorino.png','null');
insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Spaghetti', '0', 'spaghetti.png','null');

insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Vino Bianco', '0', 'vino_bianco.png','null');
insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Pomodori Pelati', '0', 'pelati.png','null');
insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Peperoncino Fresco', '0', 'peperoncino.png','null');
insert into ingrediente (id, nome, quantita, url_Image, udm) values(nextval('ingrediente_seq'), 'Pepe Nero', '0', 'Pepe-nero.png','null');