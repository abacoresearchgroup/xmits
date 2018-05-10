import warnings
warnings.simplefilter(action='ignore', category=FutureWarning)

import nltk


def extract_key_words(sentence, diagram_words):

    sent_tokenize = nltk.data.load('tokenizers/punkt/portuguese.pickle')

    sentences = sent_tokenize.tokenize(sentence)

    result = {'noun': {}, 'article': {}, 'verb': {}, 'adjective': {}}

    with open('punctuation.txt', 'r') as f:
        punctuation = f.read().split('\n')

    with open('article.txt', 'r') as f:
        article = f.read().split('\n')

    with open('verb.txt', 'r') as f:
        verb = f.read().split('\n')

    with open('adjective.txt', 'r') as f:
        adjective = f.read().split('\n')

    for sentence in sentences:

        for word in nltk.word_tokenize(sentence):

            word = word.lower()

            tmp_class = None

            if word in punctuation:
                print('Ignoring {}'.format(word))

            elif word in article:
                tmp_class = 'article'

            elif word in verb:
                tmp_class = 'verb'

            elif word in adjective:
                tmp_class = 'adjective'

            else:
                tmp_class = 'noun'

            if tmp_class is not None:

                update_dict(result, tmp_class, word)

                if word in diagram_words:
                    update_dict_intersect(result_intersection, tmp_class, word)


    return result


def update_dict(dictionary, key, word):
    dictionary[key][word] = 1 if word not in dictionary[key] else dictionary[key][word] + 1


