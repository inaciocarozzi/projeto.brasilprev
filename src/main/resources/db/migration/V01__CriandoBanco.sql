-- Categoria
CREATE TABLE public.categoria (
	id_categoria bigserial NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria)
);

--ALTER TABLE public.categoria OWNER TO postgres;
--GRANT ALL ON TABLE public.categoria TO postgres;

-- Endereco
CREATE TABLE public.endereco (
	id_endereco bigserial NOT NULL,
	bairro varchar(255) NULL,
	cep int4 NULL,
	cidade varchar(255) NULL,
	estado varchar(255) NULL,
	rua varchar(255) NULL,
	CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco)
);

--ALTER TABLE public.endereco OWNER TO postgres;
--GRANT ALL ON TABLE public.endereco TO postgres;

-- Cliente
CREATE TABLE public.cliente (
	id_cliente bigserial NOT NULL,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	senha varchar(255) NULL,
	endereco_id_endereco int8 NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente),
	CONSTRAINT fkh0xbiq7232e2rjfe30rv36ucr FOREIGN KEY (endereco_id_endereco) REFERENCES endereco(id_endereco)
);

--ALTER TABLE public.cliente OWNER TO postgres;
--GRANT ALL ON TABLE public.cliente TO postgres;

-- Produto
CREATE TABLE public.produto (
	id_produto bigserial NOT NULL,
	descricao varchar(255) NULL,
	nome varchar(255) NULL,
	preco float8 NULL,
	quantidade int4 NULL,
	url_foto varchar(255) NULL,
	categoria_id_categoria int8 NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id_produto),
	CONSTRAINT fk6r9tvvijj92b2kg869nlplhhq FOREIGN KEY (categoria_id_categoria) REFERENCES categoria(id_categoria)
);

--ALTER TABLE public.produto OWNER TO postgres;
--GRANT ALL ON TABLE public.produto TO postgres;

-- Pedido
CREATE TABLE public.pedido (
	id_pedido bigserial NOT NULL,
	"data" timestamp NULL,
	sessao varchar(255) NULL,
	status varchar(255) NULL,
	cliente_id_cliente int8 NULL,
	CONSTRAINT pedido_pkey PRIMARY KEY (id_pedido),
	CONSTRAINT fkr96bx33q9bmma0u00y1nmd8xc FOREIGN KEY (cliente_id_cliente) REFERENCES cliente(id_cliente)
);

--ALTER TABLE public.pedido OWNER TO postgres;
--GRANT ALL ON TABLE public.pedido TO postgres;

-- Pedido Item
CREATE TABLE public.pedido_item (
	id_item bigserial NOT NULL,
	quantidade int4 NULL,
	valor float8 NULL,
	id_pedido int8 NOT NULL,
	produto_id_produto int8 NULL,
	CONSTRAINT pedido_item_pkey PRIMARY KEY (id_item),
	CONSTRAINT fk4wk01bc6aw961shv86vkmpkcf FOREIGN KEY (produto_id_produto) REFERENCES produto(id_produto),
	CONSTRAINT fkkhwdtsmompsbn7dn0ghxhgurm FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
);

--ALTER TABLE public.pedido_item OWNER TO postgres;
--GRANT ALL ON TABLE public.pedido_item TO postgres;

