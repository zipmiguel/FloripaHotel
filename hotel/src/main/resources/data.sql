insert into tipoQuarto (tipoQuarto,quantidadeCamaSolteiro,quantidadeCamaCasal,numeroPessoas,StatusTipoQuarto) values ("Deluxe Plus",0,1,2,1),("Deluxe",0,1,2,1),("Master",2,1,4,1),("Standard",2,0,2,1);

insert into hospede (
    -- id_cartao,
    nome, telefone, cpf, endereco, email, nascimento, senha, verificarConfirmacao) values (
        -- cartao,
        "hospede0", (00)00000-0000, 000.000.000-00, "Rua hospede zero", "hospede0@email.com", 00-00-0000, "Hospede0!", "-1"), (
        -- cartao,
        "hospede1", (11)11111-1111, 111.111.111-11, "Rua hospede um", "hospede1@email.com", 11-11-1111, "Hospede1!", "-1"), (
        -- cartao,
        "hospede2", (22)22222-2222, 222.222.222-22, "Rua hospede dois", "hospede2@email.com", 22-22-2222, "Hospede2!", "-1"), (
        -- cartao,
        "hospede3", (33)33333-3333, 333.333.333-33, "Rua hospede tres", "hospede3@email.com", 33-33-3333, "Hospede3!", "-1");

insert into quarto (id_tipoQuarto, listaReservas, numero, status) values 
    (0, 0, 1, 1), 
    (0, 0, 2, 1), 
    (0, 0, 3, 1), 
    (0, 0, 4, 1), 
    (0, 0, 5, 1), 
    (0, 0, 6, 1), 
    (0, 0, 7, 1), 
    (0, 0, 8, 1), 
    (0, 0, 9, 1), 
    (0, 0, 10, 1), 
    (0, 0, 11, 1), 
    (0, 0, 12, 1),
    (0, 0, 13, 1),
    (0, 0, 14, 1),
    (0, 0, 15, 1),
    (0, 0, 16, 1),
    (0, 0, 17, 1),
    (0, 0, 18, 1),
    (0, 0, 19, 1),
    (0, 0, 20, 1),
    (0, 0, 21, 1),
    (0, 0, 22, 1),
    (0, 0, 23, 1),
    (0, 0, 24, 1),
    (0, 0, 25, 1),
    (1, 0, 26, 1),
    (1, 0, 27, 1),
    (1, 0, 28, 1),
    (1, 0, 29, 1),
    (1, 0, 30, 1),
    (1, 0, 31, 1),
    (1, 0, 32, 1),
    (1, 0, 33, 1),
    (1, 0, 34, 1),
    (1, 0, 35, 1),
    (1, 0, 36, 1),
    (1, 0, 37, 1),
    (1, 0, 38, 1),
    (1, 0, 39, 1),
    (1, 0, 40, 1),
    (1, 0, 41, 1),
    (1, 0, 42, 1),
    (1, 0, 43, 1),
    (1, 0, 44, 1),
    (1, 0, 45, 1),
    (1, 0, 46, 1),
    (1, 0, 47, 1),
    (1, 0, 48, 1),
    (1, 0, 49, 1),
    (1, 0, 50, 1),
    (2, 0, 51, 1),
    (2, 0, 52, 1),
    (2, 0, 53, 1),
    (2, 0, 54, 1),
    (2, 0, 55, 1),
    (2, 0, 56, 1),
    (2, 0, 57, 1),
    (2, 0, 58, 1),
    (2, 0, 59, 1),
    (2, 0, 60, 1),
    (2, 0, 61, 1),
    (2, 0, 62, 1),
    (2, 0, 63, 1),
    (2, 0, 64, 1),
    (2, 0, 65, 1),
    (2, 0, 66, 1),
    (2, 0, 67, 1),
    (2, 0, 68, 1),
    (2, 0, 69, 1),
    (2, 0, 70, 1),
    (2, 0, 71, 1),
    (2, 0, 72, 1),
    (2, 0, 73, 1),
    (2, 0, 74, 1),
    (2, 0, 75, 1),
    (3, 0, 76, 1),
    (3, 0, 77, 1),
    (3, 0, 78, 1),
    (3, 0, 79, 1),
    (3, 0, 80, 1),
    (3, 0, 81, 1),
    (3, 0, 82, 1),
    (3, 0, 83, 1),
    (3, 0, 84, 1),
    (3, 0, 85, 1),
    (3, 0, 86, 1),
    (3, 0, 87, 1),
    (3, 0, 88, 1),
    (3, 0, 89, 1),
    (3, 0, 90, 1),
    (3, 0, 91, 1),
    (3, 0, 92, 1),
    (3, 0, 93, 1),
    (3, 0, 94, 1),
    (3, 0, 95, 1),
    (3, 0, 96, 1),
    (3, 0, 97, 1),
    (3, 0, 98, 1),
    (3, 0, 99, 1),
    (3, 0, 100, 1);

insert into diaria (id_tipoQuarto, diaUtil, fimDeSemana, feriado, promocional) values 
-- promocional igual a 10% de desconto em relacao ao preco do diaUtil
    (0, 100, 120, 130, 90), 
    (1, 200, 220, 230, 180), 
    (2, 300, 320, 330, 270), 
    (3, 500, 520, 530, 450); 