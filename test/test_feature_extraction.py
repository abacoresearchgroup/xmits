# coding: utf-8
import unittest
import feature

class TestFeatureExtractor(unittest.TestCase):

    def test_feature_extractor(self):

        results = feature.extract_key_words("T CSGB ST=2 SST=1 endereço TCendereço [accept]TM VC [endereço inválido]TM RE [endereço válido]traduzendereço sinal Encaminha TM cmd pusosinal ","Pacote TC_CTL.TC_MudancaMododeOperacao recebido e mudança de modo é inválida. Gerar e enviar TM_RE")
        #results = feature.extract_key_words("","")
        print(results[0])
        print(results[1])
        print(results[2])
        conteudo=""
        for i in range(0, 3):
            conteudo += str(len(results[i]['adverb']))+";"
            conteudo += str(len(results[i]['adjective']))+";"
            conteudo += str(len(results[i]['article']))+";"
            conteudo += str(len(results[i]['noun']))+";"
            conteudo += str(len(results[i]['verb']))+";"
            conteudo += str(len(results[i]['preposition']))+";"
            conteudo += str(len(results[i]['pronoun']))+";"

        with open('../files/table.csv', 'a') as arq:
            arq.write(conteudo)
            arq.write('\n')
