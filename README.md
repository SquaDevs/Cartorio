# git, branch, merge e etc. 

# Video de referencia (em inglês): https://www.youtube.com/watch?v=oFYyTZwMyAg

# Caso tenha duvidas crie um repositorio de teste, va testando tudo e pesquise.

# Para trabalhar com branch (os comandos estão no final):

- De um git pull para atualizar o projeto (ou clone caso ainda não o tenho em sua maquina)

- Crie uma nova branch e mude para ela

- Faça as suas alterações e de um commit (apenas commit e não push)

- Volte para branch principal e de um git pull para ver se ouve modificações e volte para sua branch

- Caso haja modificações na branch principal de um "git merge  branch-principal"(estando na sua branch) 

- Caso de conflito tente verificar quais modificações foram feitas usando git diff branch-principal..sua-branch
ou verifique manualmente pelo proprio github e tente adequar a sua branch a branch principal

- Caso tenha feito outras modificações de commit novamente e de um git push -u origin sua-branch

- Depois disso va no repositorio no github mude para a sua branch e crie um pull request, botões em cima da lista de arquivos

- De um create pull request, depois disso a branch ficara deponivel para dar merge, não é recomendado que você  de um 
merge em seu proprio pull request, mas a reponsabilidade é sua

- Na parte da direita (Reviewers), você pode escolher alguem para revisar o seu codigo caso queira

# Comandos

- "git pull" : atualiza o seu repositorio local com o repositorio do github

- "git branch" : o comando serve para ver em qual branch esta atualmente, alem de mostrar o nome de outras branch

- "git branch nome" : irar criar uma branch com um nome que você digitar depois do comando

- "git checkout nome" : muda para a branch que você especificar depois do comando

- "git checkout -b nome" : cria uma nova branch e muda para ela, basicamente é uma combinação dos dois comandos anteriores  
"git branch nome" e "git checkout nome"

- "git merge nome" : como nome ja diz da um merge na branch atual com uma branch especifica, 
caso de conflito ao executar o comando significa que alguma linha que você alterou ja foi modificada na outra branch

- "git diff nome1..nome2" : mostra as diferenças entre duas branchs, caso o console "trave" aperte Q para sair

- "git push origin nome" : da um push na branch especificada, caso a branch não exista no github ela ja é criada automaticamente



