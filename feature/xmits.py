import warnings
warnings.simplefilter(action='ignore', category=FutureWarning)

import nltk


def extract_key_words(sentence):

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

            if word in punctuation:
                print('Ignoring {}'.format(word))

            elif word in article:
                update_dict(result, 'article', word)

            elif word in verb:
                update_dict(result, 'verb', word)

            elif word in adjective:
                update_dict(result, 'adjective', word)

            else:
                update_dict(result, 'noun', word)


    return result


def update_dict(dictionary, key, word):
    dictionary[key][word] = 1 if word not in dictionary[key] else dictionary[key][word] + 1


