# Escalonador-SO
Trabalho da disciplina de Sistemas Operacionais - Universidade Federal Fluminense

# Descrição do trabalho
Seja um sistema operacional multiprogramado que implementa escalonamento de processos com quatro prioridades e considera um número limitado de recursos disponíveis, sendo que quatro desses recursos são CPUs. Neste sistema,  todas as CPUs compartilham a memória principal. 

A interface de seu sistema deve ser tal que seja fácil seguir o que está acontecendo durante um determinado período de tempo. 

 # # Níveis de prioridade definem diferentes tipos de processos:
Os processos que são submetidos podem ser: 

---de Tempo real - de maior prioridade (prioridade 0) : 
são executados de imediato de acordo com a política FCFS, sendo executados até sua conclusão (não há interrupção)
prioritários sobre outros processos de menor prioridade
---de Usuário: prioridade normal (prioridades 1, 2, ou 3):
executados de acordo com a política de feedback com três filas de processos prontos
O quantum definido é de 2 unidades de tempo (defina sua unidade de tempo).


 # # Filas de submissão:
Duas filas de submissão são mantidas: 

fila de processos prontos do tipo tempo-real FTR
fila de processos prontos do tipo usuário FU
Essas filas são alimentadas a partir de uma fila de entrada única de processos submetidos e um agente deve distinguir os processos de entrada e e encaminhar para um das filas citadas acima. A fila de entrada é examinada a cada período de tempo (tempo este especificado pelo projetista do sistema operacional). 

A fila FTR deve estar vazia para que o escalonador feedback seja ativado, quer dizer, enquanto existir processos do tempo real prontos, esses serão escalonados primeiramente. A implementação da política feedback baseada no conceito básico apresentado em aula. 


 # # Recursos Disponíveis:
O sistema possui os seguintes recursos:

--quatro CPUs
--duas impressoras
--dois discos
--16GB de memória principal

Processos de baixa prioridade (de usuários) podem usar qualquer um dos recursos, mas o sistema é notificado de que recursos o processo vai precisar no momento da submissão do processo. O despachante assegura que cada recursos solicitado está apenas disponível para o processo requisitante durante um certo período de tempo (a ser definido pelo projetista).

Processos de tempo real não precisam de nenhum outro recursos a não ser memória principal, que será de no máximo de 512MBytes para cada processo. 


 # # Alocação de Memória:
O gerenciamento de memória alocada a cada processo deve garantir que toda a imagem do processo esteja carregado na MP, continuamento OU em páginas. Assim, um gerenciador de memória deve ser desenvolvido, podendo este implementar um dos dois modos:

--particionamento dinâmico de memória: um bloco contíguo de memória é alocado para cada processo, durante a sua vida no sistema. Mas repare, os processos em tempo real não devem ficar bloqueados por falta de memória - prioridade é dada para alocar tais processos, pois estes não podem passar tempo em fila esperando por memória
--páginação de processos: para tal, uma tabela de páginas por processo deve ser definida. A memória principal é vista como um conjunto de quadros. O projetista também deve definir o tamanho da página, e consequentemente, do quadro. 

 # # Processos:
Processos no sistema em questão são criados pelo despachante. Este processo cria qualquer processo, de qualquer prioridade e exibe as seguintes mensagens:

--uma mensagem que mostra a identificação do processo a ser criado;
--uma mensagem relata quando um processo muda de estado (para suspenso, terminado, ou continua executando). 

 # # Algumas observações importantes:
--Um processo ao ser submetido é inserido na fila de submissão, com descritor que já contém as suas características principais.
--O processo se torna pronto quando todos os seus recursos necessários estão disponíveis, inclusive memória (prioridade para processos de tempo real). 
--Quando um processo é iniciado, o despachante deve apresentar os parâmetros do processo (identificação do processo, prioridade, tempo de processamento restante (em segundos), localização e tamanho do bloco de memória e recursos solicitados).
--Quando um processo é finalizado, os recursos utilizados são devolvidos ao sistema para serem utilizados por outros processos.
