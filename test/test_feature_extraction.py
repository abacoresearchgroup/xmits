import warnings
warnings.simplefilter(action='ignore', category=FutureWarning)

import unittest
import feature


class TestFeatureExtractor(unittest.TestCase):

    def test_feature_extractor(self):

        result = feature.extract_key_words('O Lucas é legal.', diagram_words)

        print(result)

        self.assertEquals(result['noun']['lucas'], 1)

        self.assertEquals(result['article']['o'],  1)

        self.assertEquals(result['verb']['é'], 1)

        self.assertEqual(result['adjective']['legal'], 1)
